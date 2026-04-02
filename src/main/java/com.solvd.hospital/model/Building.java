package com.solvd.hospital.model;

import java.util.Objects;

public abstract class Building {

    private String name;
    private String address;

    public Building(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public abstract String toString();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return Objects.equals(name, building.name) && Objects.equals(address, building.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address);
    }
}
