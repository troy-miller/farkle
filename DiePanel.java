import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class DiePanel extends JPanel {
	//----------------------
	//Instance Variables
	//----------------------
	private Die myDie;
	private JLabel number;
	
	//----------------------
	//Constructors
	//----------------------
	public DiePanel(Die aDie) {
		myDie = aDie;
		number = new JLabel();
		this.roll();
		
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(80, 80));
		this.add(number);
		
		this.addMouseListener(new ClickListener());
	}
	
	//----------------------
	//Class Methods
	//----------------------
	public void roll() {
		myDie.roll();
		number.setText(Integer.toString(myDie.getCurrentValue()));
	}
	
	public Die getDie() {
		return myDie;
	}
	
	public void setPermaHeld() {
		this.setBackground(Color.red);
		FarkleDie farkleDie = (FarkleDie)myDie;
		farkleDie.setPermaHeld();
		farkleDie.resetHeld();
	}
	
	//----------------------
	//Inner Classes
	//----------------------
	private class ClickListener implements MouseListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			FarkleDie farkleDie = (FarkleDie)DiePanel.this.getDie();
			if (!farkleDie.getPermaHeld()) {
				if (farkleDie.getHeld()) {
					DiePanel.this.setBackground(Color.white);
					farkleDie.resetHeld();
				}
				else {
					DiePanel.this.setBackground(Color.green);
					farkleDie.setHeld();
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			//Do nothing
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			//Do nothing
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			//Do nothing
		}

		@Override
		public void mouseExited(MouseEvent e) {
			//Do nothing
		}
	}
}
