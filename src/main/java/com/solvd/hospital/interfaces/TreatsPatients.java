package com.solvd.hospital.interfaces;

import com.solvd.hospital.model.medical.Treatment;
import com.solvd.hospital.model.person.Patient;

import java.util.List;

public interface TreatsPatients {
    List<Treatment> treatPatients(List<Patient> patients);
}
