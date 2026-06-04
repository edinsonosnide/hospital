package com.solvd.hospital.model.person;

import com.solvd.hospital.enums.Gender;
import com.solvd.hospital.enums.Month;
import com.solvd.hospital.enums.PainLevel;
import com.solvd.hospital.interfaces.TreatsPatients;
import com.solvd.hospital.model.device.Smartphone;
import com.solvd.hospital.model.medical.Illness;
import com.solvd.hospital.model.medical.Medicine;
import com.solvd.hospital.model.medical.Symptom;
import com.solvd.hospital.model.medical.Treatment;
import com.solvd.hospital.model.storage.Backpack;
import com.solvd.hospital.model.storage.Box;
import com.solvd.hospital.model.storage.Briefcase;
import com.solvd.hospital.service.DiagnosisService;
import com.solvd.hospital.service.TreatmentService;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.solvd.hospital.Main.LOGGER;

public class Doctor<T, K, V> extends Employee implements TreatsPatients {


    private Box<T> box;
    private Briefcase<K> briefcase;
    private Backpack<V> backpack;

    private final DiagnosisService diagnosisService;
    private final TreatmentService treatmentService;

    public Doctor(String firstName, String lastName, BigInteger nationalId, int age, Gender gender, String address, String email, Smartphone smartphone, BigInteger monthlySalary, Box<T> box, Briefcase<K> briefcase, Backpack<V> backpack, Month monthOfBirth, DiagnosisService diagnosisService, TreatmentService treatmentService) {
        super(firstName, lastName, nationalId, age, gender, address, email, smartphone, monthlySalary, monthOfBirth);
        this.box = box;
        this.briefcase = briefcase;
        this.backpack = backpack;
        this.diagnosisService = diagnosisService;
        this.treatmentService = treatmentService;

    }

    @Override
    public List<Treatment> treatPatients(List<Patient> patients) {
        List<Treatment> treatmentsGiven = new ArrayList<>();

        for (Patient patient : patients) {
            Illness illness = diagnosisService.diagnose(patient);

            if (illness == null) {
                LOGGER.info(
                        "{} {}'s illness not found",
                        patient.getFirstName(),
                        patient.getLastName()
                );
                continue;
            }

            LOGGER.info(
                    "The patient {} {} has been found to suffer from {}",
                    patient.getFirstName(),
                    patient.getLastName(),
                    illness.getName()
            );

            Treatment treatment = treatmentService.createTreatment(
                    illness,
                    this,
                    patient
            );

            treatmentsGiven.add(treatment);
        }

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
        return backpack;
    }

    public void setBackpack(Backpack<V> backpack) {
        this.backpack = backpack;
    }
}
