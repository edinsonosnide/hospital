package com.solvd.hospital.model.device;

public abstract class ElectronicDevice extends Device {

    private boolean rechargeable;

    public ElectronicDevice(String nameOfDevice, String serial, String brand , String model, String color, boolean rechargeable) {
        super(nameOfDevice, serial, brand, model, color);
        this.rechargeable = rechargeable;
    }

    public boolean isRechargeable() { return rechargeable; }

    public void setRechargeable(boolean rechargeable) { this.rechargeable = rechargeable; }

    @Override
    public abstract String toString();

}
