package com.handy.web.HandyGuys.Models;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @Column(name = "user_id")
    private UUID userId = UUID.randomUUID();
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "password")
    private String password;
    @Column(name = "is_handy")
    private boolean isHandy;
    @Column(name = "is_active")
    private boolean isActive = true;
    @Column(name = "signup_date")
    private Date signUpDate;

    @OneToMany(mappedBy = "user1", fetch = FetchType.EAGER)
    List<Conversation> conversations1;

    @OneToMany(mappedBy = "user2", fetch = FetchType.EAGER)
    List<Conversation> conversations2;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    List<Appointment> clientAppointments;

    @OneToMany(mappedBy = "handyGuy", fetch = FetchType.EAGER)
    List<Skill> skills;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    List<Review> reviews;

    public List<Conversation> getConversations1() {
        return conversations1;
    }

    public List<Conversation> getConversations2() {
        return conversations2;
    }

    public List<Appointment> getClientAppointments() {
        return clientAppointments;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public boolean isHandy() {
        return isHandy;
    }

    public void setHandy(boolean isHandy) {
        this.isHandy = isHandy;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(Date signUpDate) {
        this.signUpDate = signUpDate;
    }

    public List<Review> getReviews() {
        return reviews;
    }

}
