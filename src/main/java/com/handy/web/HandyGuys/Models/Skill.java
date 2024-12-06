package com.handy.web.HandyGuys.Models;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
// import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "skills")
public class Skill {
    @Id
    @Column(name = "id")
    private UUID id = UUID.randomUUID();
    @Column(name = "category")
    private ECategory category;
    @Column(name = "description", length = 1000)
    private String description;
    @ManyToOne
    @JoinColumn(name = "handy_id")
    private User handyGuy;

    @OneToMany(mappedBy = "skill")
    List<Review> reviews;

    @OneToMany(mappedBy = "skill")
    List<Appointment> appointments;

    public List<Review> getReviews() {
        return reviews;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public Rating getRating() {
        return rating;
    }

    @OneToOne(mappedBy = "skill")
    Rating rating;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ECategory getCategory() {
        return category;
    }

    public void setCategory(ECategory category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getHandyGuy() {
        return handyGuy;
    }

    public void setHandyGuy(User handyGuy) {
        this.handyGuy = handyGuy;
    }

}
