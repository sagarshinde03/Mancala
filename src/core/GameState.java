package core;

import java.util.UUID;

import javax.websocket.Session;

import com.google.gson.Gson;

public class GameState {
	private Session player1;
	private Session player2;
	private int playerTurn;
	private final long uid = UUID.randomUUID().getLeastSignificantBits();

	public GameState(Session player1) {
		this.player1 = player1;
	}

	public GameState(Session player1, Session player2) {
		this.player1 = player1;
		this.player2 = player2;

	}

	public GameState() {
		this.playerTurn = 1;
		System.out.println(toJson());
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
