package com.handy.web.HandyGuys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handy.web.HandyGuys.Models.LoginTracker;
import com.handy.web.HandyGuys.repository.ILoginTrackerRepository;

@Service
public class LoginTrackerService {
    @Autowired
    ILoginTrackerRepository repo;

    public String saveTrack(LoginTracker tracker){
        repo.save(tracker);
        return "Login tracker saved successfully!";
    }
}
