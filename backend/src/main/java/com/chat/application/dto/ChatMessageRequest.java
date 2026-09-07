package com.chat.application.controller.dto;

public class ChatMessageRequest {

    private Long conversationId;
    private Long senderId;
    private String content;

    public ChatMessageRequest() {
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
}