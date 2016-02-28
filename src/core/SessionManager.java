/**
 * 
 */
package core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class SessionManager {

	private Set<Integer> sessionIds;
	private Map<Integer, GameState> sessions;
	private Random random;
	private static SessionManager instance;

	private SessionManager() {
		this.sessionIds = new HashSet<>();
		this.sessions = new HashMap<>();
		this.random = new Random();
	}

	public static SessionManager getInstance() {
		if (instance == null) {
			instance = new SessionManager();
		}
		return instance;
	}

	public int generateSessionId() {
		Integer sessionId = random.nextInt(10000);
		while (sessionIds.contains(sessionId)
		    && sessions.containsKey(sessionId)) {
			sessionId = random.nextInt(10000);
		}
		sessionIds.add(sessionId);
		return sessionId;
	}

	public GameState getSession(int sessionId) {
		if (sessions.containsKey(sessionId)) {
			return sessions.get(sessionId);
		}
		return null;
	}

	public boolean startSession(int player1, int player2) {
		this.sessionIds.remove(player1);
		this.sessionIds.remove(player2);
		this.sessions.remove(player2);
		System.out.println(sessionIds);
		System.out.println(sessions);
		return true;
	}

	public boolean createSession(int player1, GameState gs) {
		this.sessions.put(player1, gs);
		return true;
	}

	public boolean stopSession(int sessionId) {
		sessions.remove(sessionId);
		return true;
	}

	public void removeWaitingSessions(int[] sessionIds) {
		for (int sessionId : sessionIds) {
			this.sessionIds.remove((Integer) sessionId);
		}
	}
}
