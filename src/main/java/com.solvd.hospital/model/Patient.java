package com.solvd.hospital.model;

import java.math.BigInteger;
import java.util.List;

import static com.solvd.hospital.Main.LOGGER;

public class Patient extends Person implements Treatable{

    private List<Symptom> symptoms;

    public Patient(String firstName, String lastName, BigInteger nationalId, int age, Gender gender, String address, String email, Smartphone smartphone, Month monthOfBirth, List<Symptom> symptoms) {
        super(firstName, lastName, nationalId, age, gender, address, email, smartphone, monthOfBirth);
        this.symptoms = symptoms;
    }

    public List<Symptom> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<Symptom> symptoms) {
        this.symptoms = symptoms;
    }

    public void readMessage(String message) {
        LOGGER.info(getFirstName() + " is reading message: " + message);
    }

    @Override
    public String toString(){
        return "Hi, I'm Patient and my name is " + getFirstName() + " " + getLastName();
    }

    @Override
    public void followTreatment(Treatment treatment) {
        LOGGER.info("Patient " + getFirstName() + " " + getLastName() + " is following this treatment: " + "'" + treatment.getDescription() + "'");
        while (!this.symptoms.isEmpty()) {
            this.symptoms.removeFirst();
        }
    }
}
