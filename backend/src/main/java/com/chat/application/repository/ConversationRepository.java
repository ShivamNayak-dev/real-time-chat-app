package com.chat.application.repository;

import com.chat.application.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    Optional<Conversation> findByUserOneIdAndUserTwoId(Long userOneId, Long userTwoId);

    Optional<Conversation> findByUserTwoIdAndUserOneId(Long userTwoId, Long userOneId);
}