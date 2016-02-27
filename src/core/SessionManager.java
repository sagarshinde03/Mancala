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

	public boolean startSession(int sessionId) {
		GameState gs = new GameState();
		sessions.put(sessionId, gs);
		return true;
	}
	
	public boolean stopSession(int sessionId) {
		 sessions.remove(sessionId);
		 return true;
	}
}
