package com.solvd.hospital.model;

import java.util.ArrayList;
import java.util.List;

import static com.solvd.hospital.Main.LOGGER;

public class Hospital extends Building{

    @MyMarkerAnnotation
    private List<HospitalRoom> hospitalRooms;

    private List<Treatment> treatments;

    public Hospital(String name, String address, List<HospitalRoom> hospitalRooms, List<Treatment> treatments) {
        super(name, address);
        this.hospitalRooms = hospitalRooms;
        this.treatments = treatments;
    }

    @MyAnnotation(value = "My custom annotation")
    public List<HospitalRoom> getHospitalRooms() {
        return hospitalRooms;
    }

    public List<Treatment> getTreatments() { return treatments; }

    public void setHospitalRooms(List<HospitalRoom> hospitalRooms) { this.hospitalRooms = hospitalRooms; }

    public void setTreatments(List<Treatment> treatments) { this.treatments = treatments; }

    public void treatAllPatients() {
        List<Treatment> allTreatmentsGiven = new ArrayList<>();
        this.getHospitalRooms().forEach(hospitalRoom -> {
            LOGGER.info("Doctor " + hospitalRoom.getDoctor().getFirstName() + " " + hospitalRoom.getDoctor().getLastName() + " is treating all patients of room " + hospitalRoom.getRoomNumber());
            allTreatmentsGiven.addAll(hospitalRoom.getDoctor().treatPatients(hospitalRoom.getPatients()));
        });
        this.treatments.addAll(allTreatmentsGiven);
    }

    @Override
    public String toString() {
        return "Hi, Hospital here and my name is " + getName() + " and my address is " + getAddress();
    }
}
