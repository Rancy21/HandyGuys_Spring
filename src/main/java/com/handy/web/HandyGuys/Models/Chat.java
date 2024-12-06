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
@Table(name = "chats")
public class Chat {
    @Id
    @Column(name = "chat_id")
    private UUID id = UUID.randomUUID();
    @ManyToOne
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User sender;
    @Column(name = "message")
    private String message;
    @Column(name = "date_time")
    private Date dateTime;
    @Column(name = "is_new")
    private boolean isNew;

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public Conversation getConversation() {
        return conversation;
    }
    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }
    public User getSender() {
        return sender;
    }
    public void setSender(User sender) {
        this.sender = sender;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Date getDateTime() {
        return dateTime;
    }
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
    public boolean isNew() {
        return isNew;
    }
    public void setNew(boolean isNew) {
        this.isNew = isNew;
    }

    public boolean getIsNew() {
        return isNew;
    }
    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

}
