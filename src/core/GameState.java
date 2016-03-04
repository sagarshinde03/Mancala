package core;

import java.util.UUID;

import javax.websocket.Session;

import com.google.gson.Gson;

public class GameState {
	private transient Session player1;
	private transient Session player2;
	private int playerTurn;
	private int player1Score;
	private int player2Score;
	private int[] deck;
	private int lastMove;
	private final long uid = UUID.randomUUID().getLeastSignificantBits();

	public GameState() {
		initialize();
		System.out.println(toJson());
	}

	private void initialize() {
		this.playerTurn = 1;
		this.player1Score = 0;
		this.player2Score = 0;
		this.deck = new int[12];
		for (int i = 0; i < 12; i++) {
			this.deck[i] = 4;
		}
	}
	
	private void playHole(int hole) {
		int marbles = deck[hole];
		deck[hole] = 0;
		
		if (playerTurn == 1) {
			
		} else {
			
		}
		
	}

	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	public void setPlayer2(Session player2) {
		this.player2 = player2;
	}

	public Session getPlayer1() {
		return player1;
	}

	public void setPlayer1(Session player1) {
		this.player1 = player1;
	}

	public int getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(int playerTurn) {
		this.playerTurn = playerTurn;
	}

	public Session getPlayer2() {
		return player2;
	}

	public int getPlayer1Score() {
		return player1Score;
	}

	public void setPlayer1Score(int player1Score) {
		this.player1Score = player1Score;
	}

	public int getPlayer2Score() {
		return player2Score;
	}

	public void setPlayer2Score(int player2Score) {
		this.player2Score = player2Score;
	}

	public int[] getDeck() {
		return deck;
	}

	public void setDeck(int[] deck) {
		this.deck = deck;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (uid ^ (uid >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameState other = (GameState) obj;
		if (uid != other.uid)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GameState [player1=" + player1 + ", player2=" + player2
		    + ", playerTurn=" + playerTurn + ", uid=" + uid + "]";
	}
}
