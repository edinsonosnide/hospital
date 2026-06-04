package com.solvd.hospital.service;

import com.solvd.hospital.model.medical.Illness;
import com.solvd.hospital.model.medical.Treatment;
import com.solvd.hospital.model.person.Doctor;
import com.solvd.hospital.model.person.Patient;

public interface TreatmentService {

    Treatment createTreatment(
            Illness illness,
            Doctor<?, ?, ?> doctor,
            Patient patient
    );

}
