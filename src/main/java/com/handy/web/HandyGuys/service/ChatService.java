package com.handy.web.HandyGuys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.handy.web.HandyGuys.Models.Chat;
import com.handy.web.HandyGuys.repository.IChatRepository;

@Service
public class ChatService {
    
    @Autowired
    IChatRepository repo;

    public String saveChat(Chat chat) {
        repo.save(chat);
        return "Chat saved successfully";
    }


}
