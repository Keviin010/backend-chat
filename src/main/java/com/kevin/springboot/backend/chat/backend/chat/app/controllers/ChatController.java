package com.kevin.springboot.backend.chat.backend.chat.app.controllers;

import com.kevin.springboot.backend.chat.backend.chat.app.models.ChatMessage;
import com.kevin.springboot.backend.chat.backend.chat.app.services.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatMessageService service;

    @MessageMapping("/sendMessage")
    @SendTo("/topic/chat")
    public ChatMessage sendMessage(ChatMessage message) {
        message.setTimestamp(LocalDateTime.now());
        return service.save(message);
    }

    @MessageMapping("/typing")
    @SendTo("/topic/typing")
    public ChatMessage typing(ChatMessage message) {
        return message;
    }

    @GetMapping("/api/messages")
    @ResponseBody
    public List<ChatMessage> getHistory() {
        return service.findAll();
    }
}