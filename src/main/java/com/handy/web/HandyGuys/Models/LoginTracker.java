package com.handy.web.HandyGuys.Models;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "login_tracker")
public class LoginTracker {
    @Id
    @Column(name = "tracker_id")
    private UUID id = UUID.randomUUID();
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "date")
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
