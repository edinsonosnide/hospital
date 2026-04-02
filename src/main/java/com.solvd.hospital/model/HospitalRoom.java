package com.solvd.hospital.model;

import java.util.List;

import static com.solvd.hospital.Main.LOGGER;

public class HospitalRoom {

    private int roomNumber;
    private List<Patient> patients;
    private Doctor doctor;

    public HospitalRoom(int roomNumber, List<Patient> patients, Doctor doctor) {
        this.roomNumber = roomNumber;
        this.patients = patients;
        this.doctor = doctor;
    }

    public int getRoomNumber() { return roomNumber; }

    public List<Patient> getPatients() { return patients; }

    public Doctor getDoctor() { return doctor; }

    public void setRoomNumber(int roomNumber) { this.roomNumber = roomNumber; }

    public void setPatients(List<Patient> patients) { this.patients = patients; }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
        LOGGER.info("A doctor named " + this.doctor.getFirstName() + " " + this.doctor.getLastName() + " was assigned to room " + roomNumber);
    }

    public void addPatient(Patient patient) {
        LOGGER.info("A patient named " + patient.getFirstName() + " " + patient.getLastName() + " was assigned to room with id: " + roomNumber);
        patients.add(patient);
    }

}
