package com.kevin.springboot.backend.chat.backend.chat.app.services;

import com.kevin.springboot.backend.chat.backend.chat.app.models.ChatMessage;
import com.kevin.springboot.backend.chat.backend.chat.app.repositories.ChatMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final ChatMessageRepository repository;

    public ChatMessage save(ChatMessage message) {
        return repository.save(message);
    }

    public List<ChatMessage> findLast() {
        List<ChatMessage> messages = new ArrayList<>(repository.findTop50ByOrderByIdDesc());
        Collections.reverse(messages);
        return messages;
    }
}
