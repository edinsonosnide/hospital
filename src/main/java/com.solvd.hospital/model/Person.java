package com.solvd.hospital.model;

import java.math.BigInteger;
import java.util.Objects;

import static com.solvd.hospital.Main.LOGGER;

public abstract class Person {

    private String firstName;
    private String lastName;
    private BigInteger nationalId;
    private int age;
    private Gender gender;
    private String address;
    private String email;
    private Smartphone smartphone;
    private Month monthOfBirth;

    public Person(String firstName, String lastName, BigInteger nationalId, int age, Gender gender, String address, String email, Smartphone smartphone, Month monthOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalId = nationalId;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.smartphone = smartphone;
        this.monthOfBirth = monthOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public BigInteger getNationalId() { return nationalId; }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public Smartphone getPhone() {
        return smartphone;
    }

    public Month getMonthOfBirth() { return monthOfBirth; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNationalId(BigInteger nationalId) { this.nationalId = nationalId; }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(Smartphone smartphone) {
        LOGGER.info("A phone was given to: " + this.firstName + " " + this.lastName);
        this.smartphone = smartphone;
    }

    public void setMonthOfBirth(Month monthOfBirth) { this.monthOfBirth = monthOfBirth; }

    @Override
    public abstract String toString();

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(nationalId, person.nationalId);
    };

    @Override
    public int hashCode(){
        return Objects.hash(firstName, lastName, nationalId);
    };

}
