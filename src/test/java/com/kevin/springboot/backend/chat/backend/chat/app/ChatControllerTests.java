package com.kevin.springboot.backend.chat.backend.chat.app;

import com.kevin.springboot.backend.chat.backend.chat.app.controllers.ChatController;
import com.kevin.springboot.backend.chat.backend.chat.app.models.ChatMessage;
import com.kevin.springboot.backend.chat.backend.chat.app.services.ChatMessageService;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ChatControllerTests {

    private final ChatMessageService service = mock(ChatMessageService.class);
    private final ChatController controller = new ChatController(service);

    private ChatMessage message(String sender, String content, ChatMessage.MessageType type) {
        ChatMessage message = new ChatMessage();
        message.setSender(sender);
        message.setContent(content);
        message.setType(type);
        return message;
    }

    private SimpMessageHeaderAccessor accessor() {
        SimpMessageHeaderAccessor accessor = SimpMessageHeaderAccessor.create();
        accessor.setSessionAttributes(new HashMap<>());
        return accessor;
    }

    @Test
    void joinGuardaElNombreEnLaSesionYNoGuardaEnBaseDeDatos() {
        SimpMessageHeaderAccessor accessor = accessor();

        ChatMessage result = controller.sendMessage(message("Ana", "", ChatMessage.MessageType.JOIN), accessor);

        assertNotNull(result);
        assertEquals("Ana", accessor.getSessionAttributes().get("username"));
        verify(service, never()).save(any());
    }

    @Test
    void mensajeVacioSeIgnora() {
        ChatMessage result = controller.sendMessage(message("Ana", "   ", ChatMessage.MessageType.CHAT), accessor());

        assertNull(result);
        verify(service, never()).save(any());
    }

    @Test
    void mensajeSinNombreSeIgnora() {
        ChatMessage result = controller.sendMessage(message("  ", "Hola", ChatMessage.MessageType.CHAT), accessor());

        assertNull(result);
    }

    @Test
    void mensajeDeChatSeGuardaConFecha() {
        when(service.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        ChatMessage result = controller.sendMessage(message("Ana", "Hola", ChatMessage.MessageType.CHAT), accessor());

        assertEquals("Hola", result.getContent());
        assertNotNull(result.getTimestamp());
        verify(service).save(any());
    }

    @Test
    void mensajeLargoSeRecortaA255Caracteres() {
        when(service.save(any())).thenAnswer(invocation -> invocation.getArgument(0));
        String largo = "a".repeat(300);

        ChatMessage result = controller.sendMessage(message("Ana", largo, ChatMessage.MessageType.CHAT), accessor());

        assertEquals(255, result.getContent().length());
    }

    @Test
    void typingNoGuardaNada() {
        ChatMessage result = controller.typing(message("Ana", "texto", ChatMessage.MessageType.CHAT));

        assertEquals(ChatMessage.MessageType.TYPING, result.getType());
        assertEquals("", result.getContent());
        verify(service, never()).save(any());
    }
}
