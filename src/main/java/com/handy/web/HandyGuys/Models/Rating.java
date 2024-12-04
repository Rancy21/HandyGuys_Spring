package com.handy.web.HandyGuys.Models;

import java.util.UUID;

public class Rating {
    private UUID id = UUID.randomUUID();
    private Skill skill;
    private double numberOfRatings;
    private double cumulatedRatings;
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
