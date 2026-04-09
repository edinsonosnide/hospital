package com.solvd.hospital.model;

public enum PainLevel {

    LOW("Low","It is noticiable"),
    MEDIUM("Medium", "It is affecting daily routines"),
    HIGH("High", "It is the only thing I can think of"),
    EXTREME("Extreme", "I can´t stand up because of this pain");

    private final String name;
    private final String description;

    PainLevel(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
}
