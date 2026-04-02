package com.solvd.hospital.model;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

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
