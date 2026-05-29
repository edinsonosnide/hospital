package com.solvd.hospital.interfaces;

import com.solvd.hospital.model.person.Patient;

@FunctionalInterface
public interface PatientProcessor {
    void displayPatient(Patient patient);
}
