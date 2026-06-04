package com.solvd.hospital.service.diagnosis;

import com.solvd.hospital.enums.PainLevel;
import com.solvd.hospital.model.medical.Illness;
import com.solvd.hospital.model.medical.Symptom;
import com.solvd.hospital.model.person.Patient;

import java.util.List;

public class PneumoniaDiagnosisRule implements DiagnosisRule{

    @Override
    public boolean matches(Patient patient) {

        List<String> pneumoniaSymptoms = List.of(
                "Chest Pain",
                "Fever",
                "Cough",
                "Difficulty Breathing"
        );

        List<String> patientSymptoms =
                patient.getSymptoms()
                        .stream()
                        .map(Symptom::name)
                        .toList();

        return patientSymptoms.size() == pneumoniaSymptoms.size()
                && patientSymptoms.containsAll(pneumoniaSymptoms);
    }

    @Override
    public Illness getIllness() {

        Symptom chestPain =
                new Symptom("Chest Pain", PainLevel.LOW);

        Symptom fever =
                new Symptom("Fever", PainLevel.LOW);

        Symptom cough =
                new Symptom("Cough", PainLevel.LOW);

        Symptom difficultyBreathing =
                new Symptom("Difficulty Breathing", PainLevel.LOW);

        return new Illness(
                "Pneumonia",
                new Symptom[]{
                        chestPain,
                        fever,
                        cough,
                        difficultyBreathing
                }
        );
    }

}
