package com.solvd.hospital.model;

public class Illness {

    private String name;
    private Symptom[] symptoms;

    public Illness(String name, Symptom[] symptoms) {
        this.name = name;
        this.symptoms = symptoms;
    }

    public String getName() { return name;}

    public void setName(String name) { this.name = name; }

    public Symptom[] getSymptoms() { return symptoms; }

    public void setSymptoms(Symptom[] symptoms) { this.symptoms = symptoms; }

}
