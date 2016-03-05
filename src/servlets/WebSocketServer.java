package servlets;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import core.GameState;
import core.SessionManager;

@ApplicationScoped
@ServerEndpoint("/play")
public class WebSocketServer {

	private static final Logger logger = Logger.getAnonymousLogger();
	private final Gson gson = new Gson();
	private final Type type = new TypeToken<Map<String, String>>() {
	}.getType();

	@OnOpen
	public void open(Session session) {
	}

	@OnClose
	public void close(Session session) {

	}

	@OnError
	public void onError(Throwable error) {
	}

	@OnMessage
	public void handleMessage(String message, Session session) {
		Map<String, String> map = gson.fromJson(message, type);
		String event = map.get("event");
		logger.fine(map.toString());
		switch (event) {
		case "start":
			try {
				int sessionId = Integer.parseInt(map.get("sessionId"));
				GameState gs = SessionManager.getInstance()
				    .getSession(sessionId);
				gs.setPlayer2(session);
				SessionManager.getInstance().updateSession(sessionId, gs);
				Map<String, String> tempMap = new HashMap<>();
				tempMap.put("event", "ack");
				tempMap.put("message", "Friend Joined! Your turn first!");
				tempMap.put("sessionId", sessionId + "");
				gs.getPlayer1().getBasicRemote().sendText(gson.toJson(tempMap));
				gs.getPlayer2().getBasicRemote().sendText(gson.toJson(tempMap));
			} catch (IOException e) {
				logger.log(Level.SEVERE, e.getMessage(), e);
			}
			break;

		case "registration":
			int selfId = Integer.parseInt(map.get("selfId"));
			GameState gs = new GameState();
			gs.setPlayer1(session);
			SessionManager.getInstance().createSession(selfId, gs);
			break;

		case "play":
			try {
				int sessionId = Integer.parseInt(map.get("sessionId"));
				gs = SessionManager.getInstance().getSession(sessionId);
				System.out.println(gs);
				gs.playHole(Integer.parseInt(map.get("hole")));
				Map<String, String> tempMap = new HashMap<>();
				tempMap.put("event", "play");
				tempMap.put("hole", map.get("hole"));
				tempMap.put("gamestate", gs.toJson());

				gs.getPlayer1().getBasicRemote().sendText(gson.toJson(tempMap));
				gs.getPlayer2().getBasicRemote().sendText(gson.toJson(tempMap));
			} catch (Exception e) {
				logger.log(Level.SEVERE, e.getMessage(), e);
			}
			break;

		case "close":
			int sessionId = Integer.parseInt(map.get("activeSession"));
			// SessionManager.getInstance().removeWaitingSessions(sessionId);
			break;
		default:
			logger.info("Custom event occurred!");
		}

	}
}
