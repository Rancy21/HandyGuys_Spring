package com.handy.web.HandyGuys.Models;

import java.util.UUID;

public class Skill {
    private UUID id = UUID.randomUUID();
    private Category category;
    private String description;
    private User HandyGuy;

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
