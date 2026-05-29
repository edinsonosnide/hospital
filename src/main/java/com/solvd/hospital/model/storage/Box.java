package com.solvd.hospital.model.storage;

import java.util.List;

public class Box <T> {
    private List<T> things;

    public Box(List<T> things) {
        this.things = things;
    }

    public List<T> getThings() {
        return things;
    }

    public void setThings(List<T> things) {
        this.things = things;
    }
}
