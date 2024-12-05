package com.handy.web.HandyGuys.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.handy.web.HandyGuys.Models.User;
import com.handy.web.HandyGuys.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping(value = "/saveUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveStudent(@RequestBody User user) {
        if (user.getUserId() == null || user.getFirstName().trim().isEmpty() || user.getLastName().trim().isEmpty()
                || user.getEmail().trim().isEmpty() || user.getPassword().trim().isEmpty()) {
            return new ResponseEntity<>("All fields are required", HttpStatus.BAD_REQUEST);
        }

        String saveStudent = service.saveUser(user);

        if (saveStudent.equalsIgnoreCase("User already exists")) {
            return new ResponseEntity<>("User with that email already exists", HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>("user saved successfully !!", HttpStatus.OK);
        }
    }

    @GetMapping(value = "/getUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUser(@RequestParam(required = true) String email) {
        User user = service.getUser(email);
        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/updateUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@RequestBody User user, @RequestParam String email) {
        if (user.getUserId() == null || user.getFirstName().trim().isEmpty() || user.getLastName().trim().isEmpty()
                || user.getEmail().trim().isEmpty() || user.getPassword().trim().isEmpty()) {
            return new ResponseEntity<>("All fields are required", HttpStatus.BAD_REQUEST);
        }
        String updateUser = service.updateUser(user, email);
        if (updateUser.equalsIgnoreCase("User not found")) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(updateUser, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/deleteUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteUser(@RequestParam(required = true) String email) {
        String deleteUser = service.deleteUser(email);
        if (deleteUser.equalsIgnoreCase("User not found")) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(deleteUser, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/getLatestSignUpList", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getLatestSignUpList() {
        List<User> latestSignUP = service.getLatestSignUpList();
        if (latestSignUP.isEmpty() || latestSignUP == null) {
            return new ResponseEntity<>("No sign up this month", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(latestSignUP, HttpStatus.OK);
        }
    }

}
