package com.handy.web.HandyGuys.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.handy.web.HandyGuys.Models.Conversation;

@Repository
public interface IConversationRepository extends JpaRepository<Conversation, UUID> {

}
