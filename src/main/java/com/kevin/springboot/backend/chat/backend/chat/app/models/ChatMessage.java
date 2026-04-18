package com.kevin.springboot.backend.chat.backend.chat.app.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "messages")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sender;
    private String content;

    @Enumerated(EnumType.STRING)
    private MessageType type;

    private LocalDateTime timestamp;

    public enum MessageType {
        CHAT, JOIN, LEAVE, TYPING
    }
}