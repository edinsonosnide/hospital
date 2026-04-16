package com.solvd.hospital.model;

@FunctionalInterface
public interface SymptomFilter {
    boolean matches(Symptom symptom);
}
