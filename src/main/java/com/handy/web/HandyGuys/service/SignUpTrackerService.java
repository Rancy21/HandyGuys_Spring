package com.handy.web.HandyGuys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handy.web.HandyGuys.Models.SignUpTracker;
import com.handy.web.HandyGuys.repository.ISignUpTrackerRepository;

@Service
public class SignUpTrackerService {

    @Autowired
    ISignUpTrackerRepository repo;

    public String saveTracker(SignUpTracker signUT) {
        repo.save(signUT);
        return "SignUp Tracker saved successfully";
    }
}
