package com.handy.web.HandyGuys.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.handy.web.HandyGuys.Models.Chat;
import com.handy.web.HandyGuys.Models.Conversation;
import com.handy.web.HandyGuys.repository.IChatRepository;

@Service
public class ChatService {
    
    @Autowired
    IChatRepository repo;

    public String saveChat(Chat chat) {
        repo.save(chat);
        return "Chat saved successfully";
    }

    public Chat getChat(String chatId){
        Optional<Chat> chat = repo.findById(UUID.fromString(chatId));
        return chat.get();
    }

    public List<Chat> getChatsPerConversation(Conversation conversation){
        return repo.findAllByConversation(conversation);
    }


}
