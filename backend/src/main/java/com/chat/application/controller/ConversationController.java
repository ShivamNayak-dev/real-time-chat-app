package com.chat.application.controller;

import com.chat.application.entity.Conversation;
import com.chat.application.service.ConversationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/conversations")
public class ConversationController {

    private final ConversationService conversationService;

    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @PostMapping
    public Conversation createConversation(
            @RequestParam Long userOneId,
            @RequestParam Long userTwoId) {

        return conversationService.createConversation(userOneId, userTwoId);
    }
}