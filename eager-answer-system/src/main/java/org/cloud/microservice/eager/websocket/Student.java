package org.cloud.microservice.eager.websocket;

import org.springframework.web.socket.WebSocketSession;

public class Student {
	private String name;
    private WebSocketSession session;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public WebSocketSession getSession() {
		return session;
	}
	public void setSession(WebSocketSession session) {
		this.session = session;
	}
	 
	

}
