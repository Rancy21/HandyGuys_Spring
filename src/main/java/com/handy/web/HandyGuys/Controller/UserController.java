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
import com.handy.web.HandyGuys.service.EmailService;
import com.handy.web.HandyGuys.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private EmailService emailService;

    public class OtpClass {
        private int OTP;

        public int getOtp() {
            return OTP;
        }

        public void setOtp(int OTP) {
            this.OTP = OTP;
        }

    }

    @GetMapping(value = "/sendOTPbyEmail", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendEmail(@RequestParam String to, @RequestParam String condition) {
        String subject = "Password Reset";
        if(condition.equalsIgnoreCase("login")){
            subject = "Verify you email address.";
        }
        // Generate OTP here
        int OTP = (int) (Math.random() * 100000);
        OtpClass otpClass = new OtpClass();
        otpClass.setOtp(OTP);

        String text = "Your OTP is: \n" + OTP + "\n You can just ingore this message " +
                "if you are not the one who requested a password reset.";
        if(condition.equalsIgnoreCase("login")){
            text = "Your OTP is: \n" + OTP;
        }
        emailService.sendSimpleEmail(to, subject, text);
        return new ResponseEntity<>(otpClass, HttpStatus.OK);
    }

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
            return new ResponseEntity<>("User With that email doesn't exist", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/updateUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@RequestBody User user, @RequestParam String email) {
        if (user.getUserId() == null || user.getFirstName().trim().isEmpty() || user.getLastName().trim().isEmpty()
                || user.getEmail().trim().isEmpty()) {
            return new ResponseEntity<>("All fields are required", HttpStatus.BAD_REQUEST);
        }
        String updateUser = service.updateUser(user, email);
        if (updateUser.equalsIgnoreCase("User not found")) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        } else if (updateUser.equalsIgnoreCase("user with Email already exists")) {
            return new ResponseEntity<>("This email is already in use", HttpStatus.CONFLICT);
        } else {
            return new ResponseEntity<>(updateUser, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/updatePassword", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updatePassword(@RequestBody User user, @RequestParam String email) {
        if (user.getPassword().trim().isEmpty()) {
            return new ResponseEntity<>("Please enter a valid password", HttpStatus.BAD_REQUEST);
        }
        String updateUser = service.updatePassword(user.getPassword(), email);
        if (updateUser.equalsIgnoreCase("User not found")) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(updateUser, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/deleteUser", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @GetMapping(value = "/gethelpers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getHelpers() {
        List<User> helpers = service.getAllHelpers();
        if (helpers.isEmpty() || helpers == null) {
            return new ResponseEntity<>("No helpers for now", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(helpers, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/getAllUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUsers() {
        List<User> helpers = service.getAllUsers();
        if (helpers.isEmpty() || helpers == null) {
            return new ResponseEntity<>("No Users for now", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(helpers, HttpStatus.OK);
        }
    }

}
