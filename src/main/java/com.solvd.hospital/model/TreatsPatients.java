package com.solvd.hospital.model;

import java.util.List;

public interface TreatsPatients {
    List<Treatment> treatPatients(List<Patient> patients);
}
