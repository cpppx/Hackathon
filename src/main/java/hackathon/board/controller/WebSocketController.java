package hackathon.board.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author cst
 */
@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@ServerEndpoint("/websocket/{bookId}")
@Component
public class WebSocketController {
  private static Map<Integer, Set<Session>> sessionMap = new ConcurrentHashMap<>();

  @OnOpen
  public void onOpen(Session session, @PathParam("bookId") int bookId) {
    if (!sessionMap.containsKey(bookId)) {
      sessionMap.put(bookId, new CopyOnWriteArraySet<>());
    }

    sessionMap.get(bookId).add(session);
  }

  @OnClose
  public void onClose(Session session, @PathParam("bookId") int bookId) {
    if (sessionMap.containsKey(bookId)) {
      sessionMap.get(bookId).remove(session);
    }
  }

  static void sendMessage(int bookId, String message) throws IOException {
    if (sessionMap.containsKey(bookId)) {
      Set<Session> sessions = sessionMap.get(bookId);
      for (Session session : sessions) {
        session.getBasicRemote().sendText(message);
      }
    }
  }
}
