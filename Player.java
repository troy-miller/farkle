

public class Player {
	//----------------------
	//Instance Variables
	//----------------------
	private String name;
	private int score;
	private boolean isGameOver;
	
	//----------------------
	//Constructors
	//----------------------
	public Player(String aName) {
		name = aName;
		score = 0;
		isGameOver = false;
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

	public boolean isGameOver() {
		return isGameOver;
	}

	public void setGameOver(boolean isGameOver) {
		this.isGameOver = isGameOver;
	}
}
