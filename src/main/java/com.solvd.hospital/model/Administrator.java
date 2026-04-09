package com.solvd.hospital.model;

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
