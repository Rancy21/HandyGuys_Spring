package com.handy.web.HandyGuys.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.handy.web.HandyGuys.Models.Chat;
import com.handy.web.HandyGuys.Models.Conversation;
import com.handy.web.HandyGuys.Models.LoginTracker;
import com.handy.web.HandyGuys.Models.User;
import com.handy.web.HandyGuys.service.LoginTrackerService;
import com.handy.web.HandyGuys.service.UserService;

@RestController
@RequestMapping(value = "/loginTracker")
public class LoginTrackerController {

    @Autowired
    private LoginTrackerService trackerService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/saveTracker", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveChat(@RequestBody LoginTracker tracker, @RequestParam String userEmail) {
                
        //Fetch the user and check if it exists
        User user = userService.getUser(userEmail);
        if (user == null) {
            //If not, return not found status
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        //Check that the date is not null        
        if(tracker.getDate() == null) 
            return new ResponseEntity<>("Missing date", HttpStatus.BAD_REQUEST);
        
        //Once everything has been validated add the user to the tracker
        tracker.setUser(user);
        
        //Save the tracker
        String saveTracker = trackerService.saveTrack(tracker);
        if (saveTracker.equalsIgnoreCase("Tracker saved successfully")) {
            return new ResponseEntity<>("Tracker saved", HttpStatus.OK);
        }
        return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
    }

}
