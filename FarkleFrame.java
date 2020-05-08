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
	private JPanel turnPanel;
	private JLabel score;
	private JLabel turnLabel;
	private FarkleGame game;
	private FarkleTurn turn;
	private JMenuBar menuBar;
	private JMenu players;
	private JMenuItem scores;
	
	//Buttons
	private JButton rollButton;
	private JButton scoreButton;
	private JButton endButton;
	
	//----------------------
	//Constructors
	//----------------------
	public FarkleFrame(ArrayList<Player> players) {
		//Set up game
		game = new FarkleGame(players);
		turn = new FarkleTurn(game.getCurrentPlayer());
		diePanels = new ArrayList<DiePanel>();
		
		// Set up presentation
		this.initializeFrame();	
		this.createDice();
		this.createScorePanel();
		this.createButtonPanel();
		this.createTurnPanel();
		
		this.add(turnPanel);
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
		this.setSize(600, 250);
		this.setTitle("Farkle");
		
		menuBar = new JMenuBar();
		players = new JMenu("Players");
		scores = new JMenuItem("Scores");
		scores.addActionListener(new ShowLeaderboard());
		this.setJMenuBar(menuBar);
		menuBar.add(players);
		players.add(scores);
	}
	
	private void createTurnPanel() {
		turnPanel = new JPanel();
		turnPanel.setLayout(new GridBagLayout());
		turnPanel.setPreferredSize(new Dimension(550, 30));
		
		turnLabel = new JLabel(turn.getPlayer().getName() + "'s Turn");
		turnPanel.add(turnLabel);
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
		
		rollButton = new JButton("Roll");
		rollButton.addActionListener(new RollListener());
		
		scoreButton = new JButton("Score");
		scoreButton.addActionListener(new ScoreListener());
		
		endButton = new JButton("End Turn");
		endButton.addActionListener(new EndTurnListener());
		
		buttons.add(rollButton);
		buttons.add(scoreButton);
		buttons.add(endButton);
		
		rollButton.setEnabled(false);
	}
	
	private void updateScore(int newScore) {
		score.setText(Integer.toString(newScore));
	}
	
	private void updateTurn() {
		game.nextPlayer();
		turn = new FarkleTurn(game.getCurrentPlayer());
		turnLabel.setText(turn.getPlayer().getName() + "'s Turn");
	}
	
	//----------------------
	//Inner Classes
	//----------------------
	private class RollListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			int held = 0;
			ArrayList<Die> farkleDice = new ArrayList<Die>();
			for (DiePanel myDiePanel : diePanels) {
				FarkleDie farkleDie = (FarkleDie)myDiePanel.getDie();
				if (!farkleDie.getPermaHeld()) {
					myDiePanel.roll();
					farkleDice.add(farkleDie);
				}
				else {
					held ++;
				}
			}
			if (held == 6) {
				farkleDice.clear();
				for (DiePanel myDiePanel : diePanels) {
					FarkleDie farkleDie = (FarkleDie)myDiePanel.getDie();
					farkleDie.resetPermaHeld();
					myDiePanel.setBackground(Color.white);
					myDiePanel.roll();
					farkleDice.add(farkleDie);
				}
			}
			if (turn.isFarkled(farkleDice)) {
				rollButton.setEnabled(false);
				JOptionPane.showMessageDialog(FarkleFrame.this, "Farkled!");
				turn.resetScore();
				FarkleFrame.this.updateScore(0);
			}
			rollButton.setEnabled(false);
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
				rollButton.setEnabled(true);
			} catch (InvalidScoringCombinationException e1) {
				JOptionPane.showMessageDialog(FarkleFrame.this, 
						"This combination of dice is invalid. Try again or end your turn.");
			}
		}
	}
	
	private class EndTurnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			game.getCurrentPlayer().addScore(turn.getScore());
			if (game.getCurrentPlayer().getScore() >= 10000) {
				game.getCurrentPlayer().setGameOver(true);
			}
			ArrayList<Die> farkleDice = new ArrayList<Die>();
			for (DiePanel myDiePanel : diePanels) {
				FarkleDie containedDie = (FarkleDie)myDiePanel.getDie();
				farkleDice.add(containedDie);
				myDiePanel.setBackground(Color.white);
				containedDie.resetHeld();
				containedDie.resetPermaHeld();
				myDiePanel.roll();
			}
			rollButton.setEnabled(false);
			FarkleFrame.this.updateScore(0);
			FarkleFrame.this.updateTurn();
			if (game.getCurrentPlayer().isGameOver()) {
				Player winningPlayer = null;
				int winningScore = 0;
				for (Player player : game.getPlayers()) {
					if (player.getScore() > winningScore) {
						winningPlayer = player;
						winningScore = player.getScore();
					}
				}
				JOptionPane.showMessageDialog(FarkleFrame.this, 
						"Game over! " + winningPlayer.getName() + " is the winner");
			}
			if (turn.isFarkled(farkleDice)) {
				JOptionPane.showMessageDialog(FarkleFrame.this, "Farkled!");
			}
		}
	}
	
	private class ShowLeaderboard implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String message = "";
			for (Player player : game.getPlayers()) {
				message += player.getName() + ": " + player.getScore() + "\n";
			}
			JOptionPane.showMessageDialog(FarkleFrame.this, message);
		}
	}
}
