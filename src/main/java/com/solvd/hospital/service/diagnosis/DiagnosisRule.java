package com.solvd.hospital.service.diagnosis;

import com.solvd.hospital.model.medical.Illness;
import com.solvd.hospital.model.person.Patient;

public interface DiagnosisRule {

    boolean matches(Patient patient);

    Illness getIllness();

}
