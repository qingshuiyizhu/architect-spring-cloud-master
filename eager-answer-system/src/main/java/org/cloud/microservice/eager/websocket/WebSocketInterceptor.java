package org.cloud.microservice.eager.websocket;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

/*
 * 
    * @ClassName: WebSocketInterceptor 拦截器
    * @Description: Socket建立连接（握手）和断开 
    * @author LINGHUI
    * @date 2017年8月31日
    *
 */
public class WebSocketInterceptor implements HandshakeInterceptor {
	// Socket建立连接（握手）
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) {
		System.out.println("Websocket:用户[ID:"
				+ ((ServletServerHttpRequest) request).getServletRequest().getSession(false).getAttribute("username")
				+ "]已经建立连接");
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			HttpSession session = servletRequest.getServletRequest().getSession(false);
			// 标记用户
			String socketid = (String) session.getAttribute("username");
			if (socketid != null) {
			 attributes.put("username", socketid);
			} else {
				return false;
			}
		}
		return true;
	}

	// Socket 连接之后
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {
		   //System.out.println("Socket 连接之后");
	}

}
