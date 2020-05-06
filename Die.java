import java.util.Random;

public class Die {
	//----------------------
	//Instance Variables
	//----------------------
	protected int numSides;
	protected int currentValue;
	protected Random myRandomNumGenerator;
	
	//----------------------
	//Constructors
	//----------------------
	public Die() {
		numSides = 6;
		myRandomNumGenerator = new Random();
		this.roll();
	}
	
	public Die(int aNumSides) {
		numSides = aNumSides;
		myRandomNumGenerator = new Random();
		this.roll();
	}
	
	//----------------------
	//Class Methods
	//----------------------
	public void roll() {
		currentValue =  myRandomNumGenerator.nextInt(numSides) + 1;
	}
	
	public int getNumSides() {
		return numSides;
	}
	
	public void setCurrentValue(int newValue) {
		currentValue = newValue;
	}
	
	public int getCurrentValue() {
		return currentValue;
	}
}
