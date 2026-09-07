package com.chat.application.controller.dto;

import com.chat.application.entity.MessageType;

import java.time.LocalDateTime;

public class ChatMessageResponse {

    private Long id;
    private Long conversationId;
    private Long senderId;
    private String content;
    private MessageType messageType;
    private LocalDateTime createdAt;

    public ChatMessageResponse(
            Long id,
            Long conversationId,
            Long senderId,
            String content,
            MessageType messageType,
            LocalDateTime createdAt) {

        this.id = id;
        this.conversationId = conversationId;
        this.senderId = senderId;
        this.content = content;
        this.messageType = messageType;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Long getConversationId() {
        return conversationId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public String getContent() {
        return content;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}