package com.handy.web.HandyGuys.Models;

import java.util.UUID;

// import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ratings")
public class Rating {
    @Id
    @Column(name = "rating_id")
    private UUID id = UUID.randomUUID();
    @OneToOne
    @JoinColumn(name = "skill_id")
    @JsonIgnoreProperties("rating") // Ignore the "ratings" field in Skill to avoid infinite recursion private
    Skill skill;
    @Column(name = "number_of_ratings")
    private double numberOfRatings;
    @Column(name = "cumulated_ratings")
    private double cumulatedRatings;
    @Column(name = "avg_rating")
    private float avgRating;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public double getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(double numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public double getCumulatedRatings() {
        return cumulatedRatings;
    }

    public void setCumulatedRatings(double cumulatedRatings) {
        this.cumulatedRatings = cumulatedRatings;
    }

    public float getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(float avgRating) {
        this.avgRating = avgRating;
    }

}
