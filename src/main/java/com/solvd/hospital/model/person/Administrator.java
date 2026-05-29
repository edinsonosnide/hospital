package com.solvd.hospital.model.person;

import com.solvd.hospital.enums.Gender;
import com.solvd.hospital.enums.Month;
import com.solvd.hospital.exceptions.NullHospitalException;
import com.solvd.hospital.model.device.Smartphone;
import com.solvd.hospital.model.facility.Hospital;

import java.math.BigInteger;

public class Administrator extends Employee {

    private Hospital hospital;

    public Administrator(String firstName, String lastName, BigInteger nationalId, int age, Gender gender, String address, String email, Smartphone smartphone, BigInteger salary, Hospital hospital, Month monthOfBirth) {
        super(firstName, lastName, nationalId, age, gender, address, email, smartphone, salary, monthOfBirth);
        if (hospital == null) {
            throw new NullHospitalException("Hospital is null");
        }
        this.hospital = hospital;
    }

    public Hospital getHospital() { return hospital; }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    @Override
    public String toString(){
        return "Hi, I'm Administrator and my name is " + getFirstName() + " " + getLastName();
    }

}
