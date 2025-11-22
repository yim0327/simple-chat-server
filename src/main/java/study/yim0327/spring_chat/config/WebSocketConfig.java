package study.yim0327.spring_chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // STOMP 프로토콜을 통한 WebSocket 메시징 구성 활성화
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /** 클라이언트가 연결할 WebSocket 엔드포인트 설정 (/ws) */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*") // CORS 정책: 개발 단계 - 모두 허용
                .withSockJS();                  // SockJS fallback 허용
    }

    /** 메시지 라우팅 규칙 설정 */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 클라이언트가 서버로 보낼 때의 URL prefix (/app/*)
        registry.setApplicationDestinationPrefixes("/app");

        // 서버가 클라이언트에게 브로드캐스트할 때의 URL prefix (/topic/*)
        registry.enableSimpleBroker("/topic");
    }
}
