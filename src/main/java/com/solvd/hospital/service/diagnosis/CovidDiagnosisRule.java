package com.solvd.hospital.service.diagnosis;

import com.solvd.hospital.enums.PainLevel;
import com.solvd.hospital.model.medical.Illness;
import com.solvd.hospital.model.medical.Symptom;
import com.solvd.hospital.model.person.Patient;

import java.util.List;

public class CovidDiagnosisRule implements DiagnosisRule {


    @Override
    public boolean matches(Patient patient) {

        List<String> covidSymptoms = List.of(
                "Fever",
                "Cough",
                "Loss of Taste",
                "Loss of Smell"
        );

        List<String> patientSymptoms =
                patient.getSymptoms()
                        .stream()
                        .map(Symptom::name)
                        .toList();

        return patientSymptoms.size() == covidSymptoms.size()
                && patientSymptoms.containsAll(covidSymptoms);
    }

    @Override
    public Illness getIllness() {

        Symptom fever =
                new Symptom("Fever", PainLevel.LOW);

        Symptom cough =
                new Symptom("Cough", PainLevel.LOW);

        Symptom lossOfTaste =
                new Symptom("Loss of Taste", PainLevel.LOW);

        Symptom lossOfSmell =
                new Symptom("Loss of Smell", PainLevel.LOW);

        return new Illness(
                "COVID-19",
                new Symptom[]{
                        fever,
                        cough,
                        lossOfTaste,
                        lossOfSmell
                }
        );
    }

}
