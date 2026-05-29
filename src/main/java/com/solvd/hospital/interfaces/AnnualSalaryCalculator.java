package com.solvd.hospital.interfaces;

import com.solvd.hospital.model.person.Employee;

import java.math.BigInteger;

@FunctionalInterface
public interface AnnualSalaryCalculator {
    BigInteger calculateAnnualSalary(Employee employee);
}
