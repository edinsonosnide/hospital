package com.solvd.hospital.model;

import java.math.BigInteger;

@FunctionalInterface
public interface AnnualSalaryCalculator {
    BigInteger calculateAnnualSalary(Employee employee);
}
