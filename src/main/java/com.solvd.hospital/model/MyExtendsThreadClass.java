package com.solvd.hospital.model;

import static com.solvd.hospital.Main.LOGGER;

public class MyExtendsThreadClass extends Thread {

    private final String name;
    private final Runnable task;

    public MyExtendsThreadClass(Runnable task, String name) {
        this.name = name;
        this.task = task;
    }

    @Override
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

    public void runTask() {
        task.run();
    }
}
