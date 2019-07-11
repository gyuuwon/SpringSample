package net.koreate.test.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatHandler extends TextWebSocketHandler{
	private List<WebSocketSession> sessionList = new ArrayList<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("연결됨 : " + session.getId());
		sessionList.add(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String msg = message.getPayload();
		String userName = msg.split(",")[0];
		String userMessage = msg.split(",")[1];
		System.out.println(session.getId() + "로 부터 전달받음" + msg);
		for(WebSocketSession s : sessionList) {
			s.sendMessage(new TextMessage(userName+":"+userMessage));
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("연결 끊김 : " + session.getId());
		sessionList.remove(session);
	}
}
