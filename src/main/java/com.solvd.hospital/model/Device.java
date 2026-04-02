package com.solvd.hospital.model;

import java.util.Objects;

public abstract class Device {

    private String nameOfDevice;
    private String serial;
    private String brand;
    private String model;
    private String color;

    public Device(String nameOfDevice, String serial, String brand, String model, String color) {
        this.nameOfDevice = nameOfDevice;
        this.serial = serial;
        this.brand = brand;
        this.model = model;
        this.color = color;
    }

    public String getNameOfDevice() { return nameOfDevice; }
    public String getSerial() { return serial; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public String getColor() { return color; }

    public void setBrand(String brand) { this.brand = brand; }
    public void setSerial(String serial) { this.serial = serial; }
    public void setModel(String model) { this.model = model; }
    public void setColor(String color) { this.color = color; }
    public void setNameOfDevice(String nameOfDevice) { this.nameOfDevice = nameOfDevice; }

    @Override
    public abstract String toString();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return Objects.equals(serial, device.serial) && Objects.equals(brand, device.brand) && Objects.equals(model, device.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serial, brand, model);
    }
}
