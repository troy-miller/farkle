import java.util.ArrayList;

public class FarkleTurn {
	//----------------------
	//Instance Variables
	//----------------------
	private Player player;
	private int score;
	private ArrayList<FarkleDie> dice;
	private Scoring scorer;
	
	//----------------------
	//Constructors
	//----------------------
	public FarkleTurn(Player currentPlayer) {
		player = currentPlayer;
		score = 0;
		dice = new ArrayList<FarkleDie>();
		scorer = new StandardFarkleScoring();
		for (int i = 0; i < 6; i++) {
			dice.add(new FarkleDie());
		}
	}
	
	//----------------------
	//Class Methods
	//----------------------
	public Player getPlayer() {
		return player;
	}
	
	public int getScore() {
		return score;
	}
	
	public ArrayList<FarkleDie> getDice() {
		return dice;
	}
	
	public void setScore(int aScore) {
		score = aScore;
	}
	
	public void resetScore() {
		score = 0;
	}
	
	public void resetDice() {
		for (FarkleDie myDie : dice) {
			myDie.resetHeld();
		}
	}
	
	public Scoring getScorer() {
		return scorer;
	}
	
	public void addScore(ArrayList<Die> scoringDice) throws InvalidScoringCombinationException {
		int newScore = scorer.scoreDice(scoringDice);
		this.setScore(this.getScore() + newScore);
	}
	
	public boolean isFarkled(ArrayList<Die> dice) {
		return scorer.isFarkled(dice);
	}
}
