import java.util.ArrayList;

public interface Scoring {
	//Take in a set of dice and return a score
	public int scoreDice(ArrayList<Die> dice) throws InvalidScoringCombinationException;
	
	//Take in a set of dice and return if they are farkled or not
	public boolean isFarkled(ArrayList<Die> dice);
}
