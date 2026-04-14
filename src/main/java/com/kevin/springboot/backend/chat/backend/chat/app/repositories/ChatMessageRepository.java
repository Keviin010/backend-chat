package com.kevin.springboot.backend.chat.backend.chat.app.repositories;

import com.kevin.springboot.backend.chat.backend.chat.app.models.ChatMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatMessageRepository extends MongoRepository<ChatMessage, String> {
}