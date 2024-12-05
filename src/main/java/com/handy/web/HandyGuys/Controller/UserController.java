package com.handy.web.HandyGuys.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
        String saveStudent = service.saveUser(user);
        if (saveStudent.equalsIgnoreCase("User already exists")) {
            return new ResponseEntity<>("User with that email already exists", HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>("user saved successfully saved", HttpStatus.OK);
        }
    }

}
