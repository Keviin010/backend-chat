package com.kevin.springboot.backend.chat.backend.chat.app.listeners;

import com.kevin.springboot.backend.chat.backend.chat.app.models.ChatMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.time.Instant;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatEventListener {

    private final SimpMessagingTemplate messagingTemplate;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        log.info("Nueva conexión WebSocket establecida");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        Map<String, Object> attributes = accessor.getSessionAttributes();

        if (attributes == null || attributes.get("username") == null) {
            return;
        }

        ChatMessage message = new ChatMessage();
        message.setSender(attributes.get("username").toString());
        message.setContent("");
        message.setType(ChatMessage.MessageType.LEAVE);
        message.setTimestamp(Instant.now());
        messagingTemplate.convertAndSend("/topic/chat", message);
    }
}
