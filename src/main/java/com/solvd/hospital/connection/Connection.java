package com.solvd.hospital.connection;

import java.util.concurrent.atomic.AtomicInteger;

public class Connection {
    private static final AtomicInteger IDS = new AtomicInteger();
    private final int id = IDS.incrementAndGet();

    public void open()  { /* pretend to open */ }
    public void close() { /* pretend to close */ }

    @Override public String toString() { return "Connection#" + id; }
}
