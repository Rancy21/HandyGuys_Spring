package com.handy.web.HandyGuys.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.handy.web.HandyGuys.Models.LoginTracker;
import com.handy.web.HandyGuys.repository.ILoginTrackerRepository;

@Service
public class LoginTrackerService {
    @Autowired
    ILoginTrackerRepository repo;

    public String saveTrack(LoginTracker tracker) {
        repo.save(tracker);
        return "Login tracker saved successfully!";
    }

    public List<LoginTracker> getLatestLogin() {
        return repo.findAllByOrderByDateDesc().stream()
                .filter(login -> {
                    LocalDate date = login.getDate().toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();
                    LocalDate now = LocalDate.now();
                    // Check if the month and year match
                    return date.getMonthValue() == now.getMonthValue() &&
                            date.getYear() == now.getYear();
                })
                .collect(Collectors.toList());

    }
}
