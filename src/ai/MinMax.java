/**
 * 
 */
package ai;

import java.util.LinkedList;
import java.util.List;

import core.GameState;

/**
 * @author Saurabh
 *
 */
public class MinMax {

	private GameState gs;

	private List<MinMax> list;

	public MinMax(GameState gs) {
		this.gs = gs;
		list = new LinkedList<>();
	}

	public GameState getGs() {
		return gs;
	}

	public void generatePossibleMoves(int level) {
		if (gs.getWinner() > -1 || level > 4) {
			return;
		}

		System.out.println(level);
		for (int i = 0; i < 6; i++) {
			int move = (gs.getPlayerTurn() - 1) * 6 + i;
			GameState gs1 = new GameState(this.gs);
			MinMax m = new MinMax(gs1);
			m.getGs().playHole(move);
			list.add(m);
		}

		int maxScore = 0;
		List<MinMax> goodMoves = new LinkedList<>();

		if (gs.getPlayerTurn() == 1) {
			for (MinMax m2 : list) {
				if (m2.getGs().getPlayer1Score() > maxScore) {
					maxScore = m2.getGs().getPlayer1Score();
				}
			}

			for (MinMax m2 : list) {
				if (m2.getGs().getPlayer1Score() == maxScore) {
					goodMoves.add(m2);
				}
			}
		} else {
			for (MinMax m2 : list) {
				if (m2.getGs().getPlayer2Score() > maxScore) {
					maxScore = m2.getGs().getPlayer2Score();
				}
			}

			for (MinMax m2 : list) {
				if (m2.getGs().getPlayer2Score() == maxScore) {
					goodMoves.add(m2);
				}
			}
		}
		System.out.println(gs.getPlayerTurn() + ">>MAX_SCORE=> " + maxScore);
		level++;
		for (MinMax minMax : goodMoves) {
			minMax.generatePossibleMoves(level);
		}

	}

	public List<MinMax> getList() {
		return list;
	}

	public static void main(String[] args) {
		GameState gs = new GameState();
		MinMax m = new MinMax(gs);
		m.generatePossibleMoves(0);
		for (MinMax m2 : m.getList()) {
			System.out.println(m2);
		}
	}

	@Override
	public String toString() {
		return "MinMax [gs=" + gs + "list=" + list + "]";
	}

}
