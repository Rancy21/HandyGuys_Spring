package com.handy.web.HandyGuys.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.handy.web.HandyGuys.Models.Chat;
import com.handy.web.HandyGuys.Models.Conversation;
import com.handy.web.HandyGuys.Models.User;
import com.handy.web.HandyGuys.service.ChatService;
import com.handy.web.HandyGuys.service.ConversationService;
import com.handy.web.HandyGuys.service.UserService;

@RestController
@RequestMapping(value = "/chats")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private ConversationService convService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/saveChat", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveChat(@RequestBody Chat chat, @RequestParam String convId,
            @RequestParam String senderEmail) {

        // Fetch the user and check if it exists
        User user = userService.getUser(senderEmail);
        if (user == null) {
            // If not, return not found status
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        // Fetch the conversation and check if it exists
        Conversation conversation = convService.getConversationById(convId);
        if (conversation == null) {
            // If not, return not found status
            return new ResponseEntity<>("Conversation not found", HttpStatus.NOT_FOUND);
        }

        // Create a boolean to validate wether there are empty fields or not
        boolean emptyFields = false;
        if (chat.getDateTime() == null)
            emptyFields = true;

        if (chat.getMessage().isEmpty())
            emptyFields = true;

        // If there are empty fields, return a bad request status
        if (emptyFields)
            return new ResponseEntity<>("Missing required fields", HttpStatus.BAD_REQUEST);

        // Once everything has been validated add the user and the conversation to chat
        chat.setSender(user);
        chat.setConversation(conversation);
        // Save the chat
        String saveChat = chatService.saveChat(chat);
        if (saveChat.equalsIgnoreCase("Chat saved successfully")) {
            return new ResponseEntity<>("Chat saved", HttpStatus.OK);
        }
        return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "getChat", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getChat(@RequestParam String chatId) {
        Chat chat = chatService.getChat(chatId);
        if (chat == null) {
            return new ResponseEntity<>("Chat not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(chat, HttpStatus.OK);

    }

    @GetMapping(value = "getChats", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getChats(@RequestParam String convId) {
        Conversation conversation = convService.getConversationById(convId);
        if (conversation == null)  {
            return new ResponseEntity<>("Conversation not found", HttpStatus.NOT_FOUND);
        }
        List<Chat> chats = chatService.getChatsPerConversation(conversation);
        if (chats.isEmpty()) {
            return new ResponseEntity<>("No messages for this conversation.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(chats, HttpStatus.OK);

    }
}
