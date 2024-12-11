package com.handy.web.HandyGuys.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        if (existingUser.isPresent() && existingUser.get().isActive()) {
            return "User already exists";
        } else {
            repository.save(user);
            return "User saved successfully";
        }
    }

    public String updateUser(User user, String email) {
        Optional<User> existingUser = repository.findUserByEmail(email);
        if (existingUser.isPresent() && existingUser.get().isActive()) {
            Optional<User> userWithEmail = repository.findUserByEmail(user.getEmail());
            if (userWithEmail.isPresent() && !user.getEmail().equalsIgnoreCase(email)) {
                return "user with Email already exists";
            }

            User updatedUser = existingUser.get();
            updatedUser.setEmail(user.getEmail());
            updatedUser.setFirstName(user.getFirstName());
            updatedUser.setLastName(user.getLastName());
            updatedUser.setPhoneNumber(user.getPhoneNumber());
            repository.save(updatedUser);
            return "User updated successfully";
        } else {
            return "User not found";
        }
    }

    public String updatePassword(String password, String email) {
        Optional<User> existingUser = repository.findUserByEmail(email);
        if (existingUser.isPresent() && existingUser.get().isActive()) {

            User updatedUser = existingUser.get();
            updatedUser.setPassword(password);
            repository.save(updatedUser);
            return "User updated successfully";
        } else {
            return "User not found";
        }
    }

    public User getUser(String email) {
        Optional<User> user = repository.findUserByEmail(email);
        if (user.isPresent() && user.get().isActive()) {
            return user.get();
        } else {
            return null;
        }
    }

    public String deleteUser(String email) {
        Optional<User> existingUser = repository.findUserByEmail(email);
        if (existingUser.isPresent() && existingUser.get().isActive()) {
            User deletedUser = existingUser.get();
            deletedUser.setActive(false);
            repository.save(deletedUser);
            return "User deleted successfully";
        } else {
            return "User not found";
        }
    }

    public List<User> getLatestSignUpList() {
        return repository.findAllByOrderBySignUpDateDesc().stream()
                .filter(user -> {
                    LocalDate signUpDate = user.getSignUpDate().toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();
                    LocalDate now = LocalDate.now();
                    // Check if the month and year match
                    return signUpDate.getMonthValue() == now.getMonthValue() &&
                            signUpDate.getYear() == now.getYear();
                })
                .collect(Collectors.toList());
    }

    public List<User> getAllHelpers() {
        return repository.findAllByIsHandyTrue();
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

}
