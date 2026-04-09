package com.solvd.hospital.model;

import java.math.BigInteger;

public abstract class Employee extends Person {

    private BigInteger salary;

    public Employee(String firstName, String lastName, BigInteger nationalId, int age, Gender gender, String address, String email, Smartphone smartphone, BigInteger salary, Month monthOfBirth) {
        super(firstName, lastName, nationalId, age, gender, address, email, smartphone, monthOfBirth);
        this.salary = salary;
    }

    public BigInteger getSalary() {
        return salary;
    }

    public void setSalary(BigInteger salary) {
        this.salary = salary;
    }

}
