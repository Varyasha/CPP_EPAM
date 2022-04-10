package com.example.cpp_epam.counter;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestCounterThread extends Thread{
    private static final Logger logger = Logger.getLogger(RequestCounterThread.class.getName());

    public RequestCounterThread() {
        super();
        start();
    }

    public void run() {
        try {
            logger.info(Thread.currentThread().getName() + " is waiting for resolution");
            Synchronization.semaphore.acquire();
            RequestCounter.increment();
            logger.info("Counter after increment " + RequestCounter.getCounter());
        } catch (InterruptedException e) {
            logger.log(Level.WARNING,"Thread was interrupted");
        }
    }
}
