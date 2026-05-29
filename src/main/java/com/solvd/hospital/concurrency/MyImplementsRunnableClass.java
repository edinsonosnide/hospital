package com.solvd.hospital.concurrency;

import static com.solvd.hospital.Main.LOGGER;

public class MyImplementsRunnableClass implements Runnable {

    private final String name;

    public MyImplementsRunnableClass(String name) {
        this.name = name;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            LOGGER.info("Iter #{}: {}",i,name);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
