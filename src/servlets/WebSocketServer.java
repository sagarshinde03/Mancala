package servlets;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

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
		System.out.println("-->>" + message);
		String event = map.get("event");
		System.out.println("$$" + map);
		switch (event) {
		case "start":
			int sessionId = Integer.parseInt(map.get("sessionId"));
			GameState gs = SessionManager.getInstance().getSession(sessionId);
			gs.setPlayer2(session);
			System.out.println("GameState" + gs);
			try {
				System.out.println(gs.getPlayer1());
				gs.getPlayer1().getBasicRemote().sendText("Hey! I am there!");
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case "registration":
			int selfId = Integer.parseInt(map.get("selfId"));
			gs = new GameState();
			gs.setPlayer1(session);
			SessionManager.getInstance().createSession(selfId, gs);
			System.out.println("GameState" + gs);
			break;
			
		case "play":
			
			break;
		default:
			System.out.println("Some custom event occurred!");
		}

	}
}
