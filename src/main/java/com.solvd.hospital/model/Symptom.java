package com.solvd.hospital.model;

public class Symptom {

    private final String name;
    private final PainLevel painLevel;

    public Symptom(String name, PainLevel painLevel) {
        this.name = name;
        this.painLevel = painLevel;
    }

    public String getName() {
        return name;
    }
    public PainLevel getPainLevel() { return  painLevel; }
}
