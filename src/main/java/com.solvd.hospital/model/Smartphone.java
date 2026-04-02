package com.solvd.hospital.model;

import static com.solvd.hospital.Main.LOGGER;

public class Smartphone extends ElectronicDevice implements Rechargable, AudioPlayer, PhotoTaker {
    private int percentageOfBattery;

    private String number;

    public Smartphone(String nameOfDevice, String serial, String brand, String model, String color, String number, boolean rechargeable, int percentageOfBattery) {
        super(nameOfDevice, serial, brand, model, color, rechargeable);
        this.number = number;
        this.percentageOfBattery = percentageOfBattery;
    }

    public String getNumber() { return number; }

    public void setNumber(String number) { this.number = number; }

    public void sendMessage(Administrator administrator, Patient patient, String message) {
        LOGGER.info(administrator.getFirstName() + " is sending a message: " + message);
        patient.getPhone().readMessage(patient, message);
    }

    public void readMessage(Patient patient, String message) {
        LOGGER.info(patient.getFirstName() + " is reading a message: " + message);
    }

    @Override
    public String toString(){
        return "This smartphone's number is: " + number + " and serial " + getSerial();
    }

    @Override
    public void rechargeBatteryCompletely() {
        this.percentageOfBattery = 100;
    }

    @Override
    public void getPercentageOfBattery() {
        LOGGER.info("Level of current battery is: " + percentageOfBattery + "%");
    }

    @Override
    public void playSong(String songName) {
        LOGGER.info("Playing song: " + songName);
    }

    @Override
    public void takeSelfie() {
        LOGGER.info("Taking selfie");
    }

}
