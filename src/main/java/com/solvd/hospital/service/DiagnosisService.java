package com.solvd.hospital.service;

import com.solvd.hospital.model.medical.Illness;
import com.solvd.hospital.model.person.Patient;

public interface DiagnosisService {

    Illness diagnose(Patient patient);

}
