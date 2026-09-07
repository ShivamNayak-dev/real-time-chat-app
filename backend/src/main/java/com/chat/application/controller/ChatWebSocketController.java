package com.chat.application.controller;

import com.chat.application.controller.dto.ChatMessageRequest;
import com.chat.application.controller.dto.ChatMessageResponse;
import com.chat.application.service.MessageService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatWebSocketController {

    private final MessageService messageService;
    private final SimpMessagingTemplate messagingTemplate;

    public ChatWebSocketController(
            MessageService messageService,
            SimpMessagingTemplate messagingTemplate) {

        this.messageService = messageService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat.send")
    public void sendMessage(ChatMessageRequest request) {

        ChatMessageResponse response =
                messageService.sendMessage(request);

        messagingTemplate.convertAndSend(
                "/topic/conversations/" + response.getConversationId(),
                response
        );
    }
}