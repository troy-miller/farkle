import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;

public class FarkleFrame extends JFrame{
	//----------------------
	//Instance Variables
	//----------------------
	private ArrayList<DiePanel> diePanels;
	private JPanel dice;
	private JPanel buttons;
	private JPanel scorePanel;
	private JLabel score;
	private FarkleGame game;
	private FarkleTurn turn;
	private ArrayList<Player> testPlayers;
	
	//----------------------
	//Constructors
	//----------------------
	public FarkleFrame() {
		//Set up game
		testPlayers = new ArrayList<Player>();
		testPlayers.add(new Player("P1"));
		game = new FarkleGame(testPlayers);
		turn = new FarkleTurn(game.getCurrentPlayer());
		diePanels = new ArrayList<DiePanel>();
		
		// Set up presentation
		this.initializeFrame();	
		this.createDice();
		this.createScorePanel();
		this.createButtonPanel();
		
		this.add(dice);
		this.add(scorePanel);
		this.add(buttons);
		
		this.setVisible(true);
	}
	
	//----------------------
	//Helper Methods
	//----------------------
	private void initializeFrame() {
		this.setLayout(new FlowLayout());
		this.setSize(600, 200);
		this.setTitle("Farkle");
	}
	
	private void createScorePanel() {
		scorePanel = new JPanel();
		scorePanel.setLayout(new GridBagLayout());
		scorePanel.setPreferredSize(new Dimension(60, 30));
		
		JLabel scoreLabel = new JLabel("Score: ");
		score = new JLabel("0");
		scorePanel.add(scoreLabel);
		scorePanel.add(score);
	}
	
	private void createDice() {
		dice = new JPanel();
		dice.setPreferredSize(new Dimension(600, 100));
		
		for (Die myDie : turn.getDice()) {
			diePanels.add(new DiePanel(myDie));
		}
		
		for (DiePanel myDiePanel : diePanels) {
			dice.add(myDiePanel);
		}
	}
	
	private void createButtonPanel() {
		buttons = new JPanel();
		
		JButton rollButton = new JButton("Roll");
		rollButton.addActionListener(new RollListener());
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ResetListener());
		JButton scoreButton = new JButton("Score");
		scoreButton.addActionListener(new ScoreListener());
		
		buttons.add(rollButton);
		buttons.add(resetButton);
		buttons.add(scoreButton);
	}
	
	private void updateScore(int newScore) {
		score.setText(Integer.toString(newScore));
	}
	
	//----------------------
	//Inner Classes
	//----------------------
	private class RollListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			for (DiePanel myDiePanel : diePanels) {
				FarkleDie farkleDie = (FarkleDie)myDiePanel.getDie();
				if (!farkleDie.getPermaHeld()) {
					myDiePanel.roll();
				}
			}
		}
	}
	
	private class ResetListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			for (DiePanel myDiePanel : diePanels) {
				FarkleDie containedDie = (FarkleDie)myDiePanel.getDie();
				if (containedDie.getHeld()) {
					myDiePanel.setBackground(Color.white);
					containedDie.resetHeld();
				}
			}
		}
	}
	
	private class ScoreListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			ArrayList<Die> savedDice = new ArrayList<Die>();
			ArrayList<DiePanel> savedDiePanels = new ArrayList<DiePanel>();
			for (DiePanel diePanel : diePanels) {
				FarkleDie farkleDie = (FarkleDie)diePanel.getDie();
				if (farkleDie.getHeld()) {
					savedDice.add(farkleDie);
					savedDiePanels.add(diePanel);
				}
			}
			try {
				turn.addScore(savedDice);
				int newScore = turn.getScore();
				FarkleFrame.this.updateScore(newScore);
				for (DiePanel diePanel : savedDiePanels) {
					diePanel.setPermaHeld();
				}
			} catch (InvalidScoringCombinationException e1) {
				JOptionPane.showMessageDialog(FarkleFrame.this, 
						"This combination of dice is invalid. Try again or end your turn.");
			}
		}
	}
}
