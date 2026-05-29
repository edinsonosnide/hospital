package com.solvd.hospital.interfaces;

import com.solvd.hospital.model.medical.Symptom;

@FunctionalInterface
public interface SymptomFilter {
    boolean matches(Symptom symptom);
}
