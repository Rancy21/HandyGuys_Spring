package com.handy.web.HandyGuys.Controller;

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

import com.handy.web.HandyGuys.Models.Conversation;
import com.handy.web.HandyGuys.Models.User;
import com.handy.web.HandyGuys.service.ConversationService;
import com.handy.web.HandyGuys.service.UserService;

@RestController
@RequestMapping(value = "/conversations")
public class ConversationController {

    @Autowired
    private ConversationService convService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/createConversation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createConversation(@RequestBody Conversation conversation,
            @RequestParam String email1, @RequestParam String email2) {

        // Fetch the users and check if they exist
        User user1 = userService.getUser(email1);
        if (user1 == null) {
            // If not, return not found status
            return new ResponseEntity<>("User1 not found", HttpStatus.NOT_FOUND);
        }
        User user2 = userService.getUser(email2);
        if (user2 == null) {
            // If not, return not found status
            return new ResponseEntity<>("User2 not found", HttpStatus.NOT_FOUND);
        }

        // Once everything has been validated add the users to conversation
        conversation.setUser1(user1);
        conversation.setUser2(user2);

        // Save conversation
        String saveConversation = convService.saveConversation(conversation);
        if (saveConversation.equalsIgnoreCase("Conversation saved successfully")) {
            return new ResponseEntity<>("Conversation saved", HttpStatus.OK);
        }
        return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
    }


    @GetMapping(value = "/getConversation", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getConversation(@RequestParam String convId){

        // Fetch the conversation and check if it exists
        Conversation conversation = convService.getConversationById(convId);
        if (conversation == null) {
            // If not, return not found status
            return new ResponseEntity<>("Conversation not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(conversation, HttpStatus.OK);
    }
}
