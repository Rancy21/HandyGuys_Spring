package com.handy.web.HandyGuys.Models;

import java.util.UUID;

public class Conversation {
    private UUID id = UUID.randomUUID();
    private User user1;
    private User user2;
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public User getUser1() {
        return user1;
    }
    public void setUser1(User user1) {
        this.user1 = user1;
    }
    public User getUser2() {
        return user2;
    }
    public void setUser2(User user2) {
        this.user2 = user2;
    }
}
