package com.solvd.hospital.service;

import com.solvd.hospital.model.medical.Illness;
import com.solvd.hospital.model.medical.Medicine;
import com.solvd.hospital.model.medical.Treatment;
import com.solvd.hospital.model.person.Doctor;
import com.solvd.hospital.model.person.Patient;

import java.time.LocalDate;

public class StandardTreatmentService implements TreatmentService {

    @Override
    public Treatment createTreatment(
            Illness illness,
            Doctor<?, ?, ?> doctor,
            Patient patient
    ) {
        Medicine amoxicillin = new Medicine(
                "amoxicillin",
                "penicillin family antibiotic"
        );

        return new Treatment(
                LocalDate.now(),
                "Take amoxicillin every day.",
                illness,
                new Medicine[]{amoxicillin},
                doctor,
                patient
        );
    }

}
