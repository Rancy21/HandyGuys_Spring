package com.handy.web.HandyGuys.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.handy.web.HandyGuys.Models.Conversation;

public interface IConversationRepository extends JpaRepository<Conversation, UUID> {
    
}
