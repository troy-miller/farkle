import java.util.ArrayList;

public class FarkleGame {
	//----------------------
	//Instance Variables
	//----------------------
	private ArrayList<Player> players;
	private Player currentPlayer;
	private int playerIndex;
	
	//----------------------
	//Constructors
	//----------------------
	public FarkleGame(ArrayList<Player> playerSet) {
		players = playerSet;
		playerIndex = 0;
		currentPlayer = players.get(playerIndex);
	}
	
	//----------------------
	//Class Methods
	//----------------------
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public void nextPlayer() {
		playerIndex = (playerIndex + 1) % players.size();
		currentPlayer = players.get(playerIndex);
	}
}
