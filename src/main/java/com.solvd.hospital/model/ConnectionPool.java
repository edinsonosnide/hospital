package com.solvd.hospital.model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static com.solvd.hospital.Main.LOGGER;

public final class ConnectionPool {
    private static final int POOL_SIZE = 5;
    private final BlockingQueue<Connection> free = new LinkedBlockingQueue<>(POOL_SIZE);
    private ConnectionPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            Connection c = new Connection(); c.open(); free.offer(c);
        }
    }
    private static class Holder {
        static final ConnectionPool INSTANCE = new ConnectionPool();
    }
    public static ConnectionPool getInstance() { return Holder.INSTANCE; }

    public Connection acquire() throws InterruptedException {
        return free.take();   // blocks until a connection is available
    }

    public void release(Connection c) throws InterruptedException {
        free.put(c);          // returns immediately (queue cannot be full here)
    }

    public void shutdown() {
        for (Connection c : free) c.close();
        free.clear();
    }
}