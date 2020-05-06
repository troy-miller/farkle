import java.util.Random;

public class FarkleDie extends Die {
	//----------------------
	//Instance Variables
	//----------------------
	private boolean isHeld;
	private boolean isPermaHeld;
	
	//----------------------
	//Constructors
	//----------------------
	public FarkleDie() {
		isHeld = false;
		isPermaHeld = false;
		numSides = 6;
		myRandomNumGenerator = new Random();
		this.roll();
	}
	
	//----------------------
	//Class Methods
	//----------------------
	public boolean getHeld() {
		return isHeld;
	}
	
	public boolean getPermaHeld() {
		return isPermaHeld;
	}
	
	public void setHeld() {
		isHeld = true;
	}
	
	public void setPermaHeld() {
		isPermaHeld = true;
	}
	
	public void resetHeld() {
		isHeld = false;
	}
	
	public void resetPermaHeld() {
		isPermaHeld = false;
	}
}
