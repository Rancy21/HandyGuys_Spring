package com.handy.web.HandyGuys.Models;

public enum ECategory {
    Plumbing("Plumbing"),
    Cleaning("Cleaning"),
    Electrical_repair("Electrical Repair");

    private final String displayName;

    ECategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
