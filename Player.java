
public class Player {
	//----------------------
	//Instance Variables
	//----------------------
	private String name;
	private int score;
	
	//----------------------
	//Constructors
	//----------------------
	public Player(String aName) {
		name = aName;
		score = 0;
	}
	
	//----------------------
	//Class Methods
	//----------------------
	public String getName() {
		return name;
	}
	
	public int getScore() {
		return score;
	}
	
	public void addScore(int aScore) {
		score += aScore;
	}
}
