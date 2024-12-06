package com.handy.web.HandyGuys.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.handy.web.HandyGuys.Models.Chat;
import com.handy.web.HandyGuys.Models.Conversation;

@Repository
public interface IChatRepository extends JpaRepository<Chat, UUID> {

    List<Chat> findAllByConversation(Conversation conversation);
}
