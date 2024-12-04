package com.handy.web.HandyGuys.Models;

import java.util.Date;
import java.util.UUID;

public class LoginTracker {
    private UUID id;
    private User user;
    private Date date;
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    
}
