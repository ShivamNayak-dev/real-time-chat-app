package com.chat.application.service;

import com.chat.application.entity.Conversation;
import com.chat.application.entity.User;
import com.chat.application.repository.ConversationRepository;
import com.chat.application.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ConversationService {

    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;

    public ConversationService(
            ConversationRepository conversationRepository,
            UserRepository userRepository) {
        this.conversationRepository = conversationRepository;
        this.userRepository = userRepository;
    }

    public Conversation createConversation(Long userOneId, Long userTwoId) {

        if (userOneId.equals(userTwoId)) {
            throw new IllegalArgumentException("Users must be different");
        }

        return conversationRepository
                .findByUserOneIdAndUserTwoId(userOneId, userTwoId)
                .or(() -> conversationRepository
                        .findByUserTwoIdAndUserOneId(userOneId, userTwoId))
                .orElseGet(() -> {

                    User userOne = userRepository.findById(userOneId)
                            .orElseThrow(() -> new RuntimeException("User one not found"));

                    User userTwo = userRepository.findById(userTwoId)
                            .orElseThrow(() -> new RuntimeException("User two not found"));

                    return conversationRepository.save(
                            new Conversation(userOne, userTwo)
                    );
                });
    }
}