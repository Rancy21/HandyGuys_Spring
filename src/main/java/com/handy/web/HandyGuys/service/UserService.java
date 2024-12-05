package com.handy.web.HandyGuys.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handy.web.HandyGuys.Models.User;
import com.handy.web.HandyGuys.repository.IUserRepository;

@Service
public class UserService {

    @Autowired
    private IUserRepository repository;

    public String saveUser(User user) {
        Optional<User> existingUser = repository.findUserByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return "User already exists";
        } else {
            repository.save(user);
            return "User saved successfully";
        }
    }

    public String updateUser(User user, String email) {
        Optional<User> existingUser = repository.findUserByEmail(email);
        if (existingUser.isPresent()) {
            Optional<User> userWithEmail = repository.findUserByEmail(user.getEmail());
            if (userWithEmail.isPresent()) {
                return "user with Email already exists";
            }
            User updatedUser = existingUser.get();
            updatedUser.setEmail(user.getEmail());
            updatedUser.setFirstName(user.getFirstName());
            updatedUser.setLastName(user.getLastName());
            updatedUser.setHandy(user.isHandy());
            updatedUser.setPhoneNumber(user.getPhoneNumber());
            updatedUser.setPassword(user.getPassword());
            repository.save(updatedUser);
            return "User updated successfully";
        } else {
            return "User not found";
        }
    }

    public String deleteUser(String email) {
        Optional<User> existingUser = repository.findUserByEmail(email);
        if (existingUser.isPresent()) {
            User deletedUser = existingUser.get();
            deletedUser.setActive(false);
            repository.save(deletedUser);
            return "User deleted successfully";
        } else {
            return "User not found";
        }
    }
}
