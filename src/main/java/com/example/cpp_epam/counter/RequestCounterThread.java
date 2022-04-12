package com.example.cpp_epam.counter;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestCounterThread extends Thread{
    private static final Logger logger = Logger.getLogger(RequestCounterThread.class.getName());
    private final Semaphore semaphore;

    public RequestCounterThread(Semaphore semaphore) {
        this.semaphore = semaphore;
        new Thread(this).start();
    }

    public void run() {
        try {
            logger.info(Thread.currentThread().getName() + " is waiting for resolution");
            semaphore.acquire();
            RequestCounter.increment();
            logger.info("Counter after increment " + RequestCounter.getCounter());
            semaphore.release();
        } catch (InterruptedException e) {
            logger.log(Level.WARNING,"Thread was interrupted");
        }
    }
}
