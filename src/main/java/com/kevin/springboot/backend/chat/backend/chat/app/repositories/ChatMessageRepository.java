package com.kevin.springboot.backend.chat.backend.chat.app.repositories;

import com.kevin.springboot.backend.chat.backend.chat.app.models.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findTop50ByOrderByIdDesc();
}
