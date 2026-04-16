package com.solvd.hospital.model;

import java.math.BigInteger;

public abstract class Employee extends Person {

    private BigInteger monthlySalary;

    public Employee(String firstName, String lastName, BigInteger nationalId, int age, Gender gender, String address, String email, Smartphone smartphone, BigInteger salary, Month monthOfBirth) {
        super(firstName, lastName, nationalId, age, gender, address, email, smartphone, monthOfBirth);
        this.monthlySalary = salary;
    }

    public BigInteger getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(BigInteger monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

}
