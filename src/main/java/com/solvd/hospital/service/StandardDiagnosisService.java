package com.solvd.hospital.service;

import com.solvd.hospital.enums.PainLevel;
import com.solvd.hospital.model.medical.Illness;
import com.solvd.hospital.model.medical.Symptom;
import com.solvd.hospital.model.person.Patient;
import com.solvd.hospital.service.diagnosis.DiagnosisRule;

import java.util.List;

public class StandardDiagnosisService implements DiagnosisService {

    private final List<DiagnosisRule> diagnosisRules;

    public  StandardDiagnosisService(List<DiagnosisRule> diagnosisRules) {
        this.diagnosisRules = diagnosisRules;
    }

    @Override
    public Illness diagnose(Patient patient) {

        for (DiagnosisRule rule : diagnosisRules) {

            if (rule.matches(patient)) {
                return rule.getIllness();
            }

        }

        return null;

    }

}
