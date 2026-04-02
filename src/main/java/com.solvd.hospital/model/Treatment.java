package com.solvd.hospital.model;

import java.time.LocalDate;

public class Treatment {

    private LocalDate time;
    private String description;
    private Illness illness;
    private Medicine[] medicines;
    private Doctor doctor;
    private Patient patient;

    public Treatment(LocalDate time, String description, Illness illness, Medicine[] medicines, Doctor doctor, Patient patient) {
        if (doctor == null || patient == null) {
            throw new NullPersonException("doctor or patient is null");
        }

        if( illness == null ) {
            throw new NullIllnessException("illness is null");
        }

        this.time = time;
        this.description = description;
        this.illness = illness;
        this.medicines = medicines;
        this.doctor = doctor;
        this.patient = patient;
    }

    public LocalDate getTime() { return time; }

    public String getDescription() { return description; }

    public Illness getIllness() { return illness; }

    public Medicine[] getMedicines() { return medicines; }

    public Doctor getDoctor() { return doctor; }

    public Patient getPatient() { return patient; }

    public void setTime(LocalDate time) { this.time = time; }

    public void setDescription(String description) { this.description = description; }

    public void setIllness(Illness illness) { this.illness = illness; }

    public void setMedicines(Medicine[] medicines) { this.medicines = medicines; }

    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

    public void setPatient(Patient patient) { this.patient = patient; }

}
