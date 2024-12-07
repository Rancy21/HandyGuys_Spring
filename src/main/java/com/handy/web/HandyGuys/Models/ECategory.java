package com.handy.web.HandyGuys.Models;

public enum ECategory {
    Electrical_repair("Electrical Repair"),
    Plumbing("Plumbing"),
    Cleaning("Cleaning");

    private final String displayName;

    ECategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
