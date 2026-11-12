package com.kevin.springboot.backend.chat.backend.chat.app.controllers;

import com.kevin.springboot.backend.chat.backend.chat.app.models.ChatMessage;
import com.kevin.springboot.backend.chat.backend.chat.app.services.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Instant;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatMessageService service;

    @MessageMapping("/sendMessage")
    @SendTo("/topic/chat")
    public ChatMessage sendMessage(ChatMessage message, SimpMessageHeaderAccessor accessor) {
        message.setSender(cut(message.getSender(), 30));
        message.setContent(cut(message.getContent(), 255));

        if (message.getSender().isEmpty() || message.getType() == null) {
            return null;
        }

        message.setId(null);
        message.setTimestamp(Instant.now());

        if (message.getType() == ChatMessage.MessageType.JOIN) {
            accessor.getSessionAttributes().put("username", message.getSender());
            message.setContent("");
            return message;
        }

        if (message.getType() == ChatMessage.MessageType.CHAT && !message.getContent().isEmpty()) {
            return service.save(message);
        }

        return null;
    }

    @MessageMapping("/typing")
    @SendTo("/topic/typing")
    public ChatMessage typing(ChatMessage message) {
        message.setSender(cut(message.getSender(), 30));
        message.setContent("");
        message.setType(ChatMessage.MessageType.TYPING);
        return message;
    }

    @GetMapping("/api/messages")
    @ResponseBody
    public List<ChatMessage> getHistory() {
        return service.findLast();
    }

    @GetMapping("/")
    @ResponseBody
    public String home() {
        return "Chat en tiempo real: servidor activo";
    }

    private String cut(String text, int max) {
        if (text == null) {
            return "";
        }
        String clean = text.trim();
        return clean.length() > max ? clean.substring(0, max) : clean;
    }
}
