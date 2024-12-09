package com.handy.web.HandyGuys.Models;

public enum ECategory {
    Electrical_repair("Electrical Repair"),
    Plumbing("Plumbing"),
    Cleaning("Cleaning"),
    Painting("Painting"),
    Housekeeping("Housekeeping"),
    Cooking("Cooking"),
    Laundry("Laundry"),
    WoodWorking("Wood Working"),
    Masonry("Masonry"),
    Driving("Driving"),
    Tutoring("Tutoring"),
    Event_Planing("Event Planing");

    private final String displayName;

    ECategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
