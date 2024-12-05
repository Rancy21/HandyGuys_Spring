package com.handy.web.HandyGuys.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handy.web.HandyGuys.Models.Conversation;
import com.handy.web.HandyGuys.repository.IConversationRepository;


@Service
public class ConversationService {
    @Autowired
    IConversationRepository repo;    

    public String saveConversation(Conversation conversation) {
        repo.save(conversation);
        return "Conversation saved successfully";
    }

    public Conversation getConversationById(String stringId) {
        return repo.findById(UUID.fromString(stringId)).orElse(null);
    }

    
}
