package com.solvd.hospital.model;

import java.util.List;

public class Briefcase <T>{
    private List<T> things;

    public Briefcase(List<T> things){
        this.things = things;
    }

    public List<T> getThings() {
        return things;
    }

    public void setThings(List<T> things) {
        this.things = things;
    }
}
