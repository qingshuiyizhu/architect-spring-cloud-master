package org.cloud.microservice.eager.websocket;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
/*
 * 
    * @ClassName: WebSocketConfig
    * @Description: WebSocket配置处理器
    * @author LINGHUI
    * @date 2017年8月31日
    *
 */
@Component
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
	@Resource
	ExamWebSocketHandler handler;

	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		
		registry.addHandler(handler, "/ws").addInterceptors(new WebSocketInterceptor());
		registry.addHandler(handler, "/ws/sockjs").addInterceptors(new WebSocketInterceptor()).withSockJS();
	}

}
