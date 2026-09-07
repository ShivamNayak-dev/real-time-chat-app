package com.chat.application.service;

import com.chat.application.controller.dto.ChatMessageRequest;
import com.chat.application.controller.dto.ChatMessageResponse;
import com.chat.application.entity.Conversation;
import com.chat.application.entity.Message;
import com.chat.application.entity.MessageType;
import com.chat.application.entity.User;
import com.chat.application.repository.ConversationRepository;
import com.chat.application.repository.MessageRepository;
import com.chat.application.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;

    public MessageService(
            MessageRepository messageRepository,
            ConversationRepository conversationRepository,
            UserRepository userRepository) {

        this.messageRepository = messageRepository;
        this.conversationRepository = conversationRepository;
        this.userRepository = userRepository;
    }

    public ChatMessageResponse sendMessage(ChatMessageRequest request) {

        Conversation conversation = conversationRepository
                .findById(request.getConversationId())
                .orElseThrow(() -> new RuntimeException("Conversation not found"));

        User sender = userRepository
                .findById(request.getSenderId())
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        Message message = new Message(
                conversation,
                sender,
                request.getContent(),
                MessageType.TEXT
        );

        Message saved = messageRepository.save(message);

        return new ChatMessageResponse(
                saved.getId(),
                conversation.getId(),
                sender.getId(),
                saved.getContent(),
                saved.getMessageType(),
                saved.getCreatedAt()
        );
    }
}