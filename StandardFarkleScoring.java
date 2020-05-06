import java.util.ArrayList;

public class StandardFarkleScoring implements Scoring {
	@Override
	public int scoreDice(ArrayList<Die> dice) throws InvalidScoringCombinationException {
		int score = 0;
		int scoredDice = 0;
		int ones = 0;
		int twos = 0;
		int threes = 0;
		int fours = 0;
		int fives = 0;
		int sixes = 0;
		
		//Get amounts of each dice
		for (Die die : dice) {
			if (die.getCurrentValue() == 1) {
				ones += 1;
			}
			else if (die.getCurrentValue() == 2) {
				twos += 1;
			}
			else if (die.getCurrentValue() == 3) {
				threes += 1;
			}
			else if (die.getCurrentValue() == 4) {
				fours += 1;
			}
			else if (die.getCurrentValue() == 5) {
				fives += 1;
			}
			else if (die.getCurrentValue() == 6) {
				sixes += 1;
			}
		}
		
		//Score ones
		if (ones == 3) {
			score += 1000;
			scoredDice += 3;
		}
		else if (ones == 2) {
			score += 200;
			scoredDice += 2;
		}
		else if (ones == 1) {
			score += 100;
			scoredDice += 1;
		}
		
		//Score fives
		if (fives == 3) {
			score += 500;
			scoredDice += 3;
		}
		else if (fives == 2) {
			score += 100;
			scoredDice += 2;
		}
		else if (fives == 1) {
			score += 50;
			scoredDice += 1;
		}
		
		//Score other values
		if (twos == 3) {
			score += 200;
			scoredDice += 3;
		}
		if (threes == 3) {
			score += 300;
			scoredDice += 3;
		}
		if (fours == 3) {
			score += 400;
			scoredDice += 3;
		}
		if (sixes == 3) {
			score += 600;
			scoredDice += 3;
		}
		
		//Check for invalid scoring
		if (score == 0 || scoredDice != dice.size()) {
			throw new InvalidScoringCombinationException();
		}
		else {
			return score;
		}
	}
	
	@Override
	public boolean isFarkled(ArrayList<Die> dice) {
		int ones = 0;
		int twos = 0;
		int threes = 0;
		int fours = 0;
		int fives = 0;
		int sixes = 0;
		
		//Get amounts of each dice
		for (Die die : dice) {
			if (die.getCurrentValue() == 1) {
				ones += 1;
			}
			else if (die.getCurrentValue() == 2) {
				twos += 1;
			}
			else if (die.getCurrentValue() == 3) {
				threes += 1;
			}
			else if (die.getCurrentValue() == 4) {
				fours += 1;
			}
			else if (die.getCurrentValue() == 5) {
				fives += 1;
			}
			else if (die.getCurrentValue() == 6) {
				sixes += 1;
			}
		}
		
		//Check for scoring dice
		if (ones > 0) {
			return false;
		}
		else if (fives > 0) {
			return false;
		}
		else if (twos == 3) {
			return false;
		}
		else if (threes == 3) {
			return false;
		}
		else if (fours == 3) {
			return false;
		}
		else if (sixes == 3) {
			return false;
		}
		else {
			return true;
		}
	}
}
