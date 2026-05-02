package com.solvd.hospital.model;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.solvd.hospital.Main.LOGGER;

public class Doctor<T, K, V> extends Employee implements TreatsPatients {


    private Box<T> box;
    private Briefcase<K> briefcase;
    private Backpack<V> Backpack;

    public Doctor(String firstName, String lastName, BigInteger nationalId, int age, Gender gender, String address, String email, Smartphone smartphone, BigInteger monthlySalary, Box<T> box, Briefcase<K> briefcase, Backpack<V> Backpack, Month monthOfBirth) {
        super(firstName, lastName, nationalId, age, gender, address, email, smartphone, monthlySalary, monthOfBirth);
        this.box = box;
        this.briefcase = briefcase;
        this.Backpack = Backpack;
    }

    @Override
    public List<Treatment> treatPatients(List<Patient> patients) {
        List<Treatment> treatmentsGiven = new ArrayList<>();

        // This doctor is bad and just know what it just googled: what symptoms does pneumonia produces
        Symptom chestPain = new Symptom("Chest Pain", PainLevel.LOW);
        Symptom fever = new Symptom("Fever", PainLevel.LOW);
        Symptom cough = new Symptom("Cough", PainLevel.LOW);
        Symptom difficultyBreathing = new Symptom("Difficulty Breathing",  PainLevel.LOW);
        List<Symptom> knownSymptomsForPneumonia = new ArrayList<>();
        knownSymptomsForPneumonia.add(chestPain);
        knownSymptomsForPneumonia.add(fever);
        knownSymptomsForPneumonia.add(cough);
        knownSymptomsForPneumonia.add(difficultyBreathing);

        List<String> namesOfKnownSymptomsForPneumonia = new ArrayList<>();
        for (Symptom symptom : knownSymptomsForPneumonia) {
            namesOfKnownSymptomsForPneumonia.add(symptom.name());
        }

        patients.forEach(patient -> {
            List<String> namesOfPatientSymptoms = new ArrayList<>();
            for (Symptom symptom : patient.getSymptoms()) {
                namesOfPatientSymptoms.add(symptom.name());
            }
            boolean illnessFound = true;
            if (namesOfKnownSymptomsForPneumonia.size() == namesOfPatientSymptoms.size()) {
                for (String patientSymptom : namesOfPatientSymptoms) {
                    if (!namesOfKnownSymptomsForPneumonia.contains(patientSymptom)) {
                        illnessFound = false;
                        break;
                    }
                }
            } else {
                illnessFound = false;
            }

            if (illnessFound) {
                LOGGER.info("The patient {} {} has been found to suffer from pneumonia", patient.getFirstName(), patient.getLastName());
                LOGGER.info("{} {} has been diagnosed and a treatment will be generated.", patient.getFirstName(), patient.getLastName());
                Illness pneumonia = new Illness("Pneumonia", new Symptom[]{chestPain, fever, cough, difficultyBreathing});
                Medicine amoxicillin = new Medicine("amoxicillin", "penicillin family antibiotic");
                Treatment treatment = new Treatment(LocalDate.now(), "Take amoxicillin every day.", pneumonia, new Medicine[]{amoxicillin}, this, patient);
                treatmentsGiven.add(treatment);
            } else {
                LOGGER.info("{} {}´s illness not found", patient.getFirstName(), patient.getLastName());
            }
        });
        return treatmentsGiven;
    }

    @Override
    public String toString() {
        return "Hi, I'm Doctor and my name is " + getFirstName() + " " + getLastName();
    }

    public Box<T> getBox() {
        return box;
    }

    public void setBox(Box<T> box) {
        this.box = box;
    }

    public Briefcase<K> getBriefcase() {
        return briefcase;
    }

    public void setBriefcase(Briefcase<K> briefcase) {
        this.briefcase = briefcase;
    }

    public Backpack<V> getBackpack() {
        return Backpack;
    }

    public void setBackpack(Backpack<V> backpack) {
        Backpack = backpack;
    }
}
