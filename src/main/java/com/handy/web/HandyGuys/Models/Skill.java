package com.handy.web.HandyGuys.Models;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
    private Category category;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "handy_id")
    private User HandyGuy;

    @OneToMany(mappedBy = "skill", fetch = FetchType.EAGER)
    List<Review> reviews;

    @OneToOne(mappedBy = "skill")
    Rating rating;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getHandyGuy() {
        return HandyGuy;
    }

    public void setHandyGuy(User handyGuy) {
        HandyGuy = handyGuy;
    }

}
