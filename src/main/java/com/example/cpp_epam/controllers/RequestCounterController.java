package com.example.cpp_epam.controllers;

import com.example.cpp_epam.counter.RequestCounter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class RequestCounterController {
    private static final Logger logger = Logger.getLogger(RequestCounterController.class.getName());

    @GetMapping(value = "/counter")
    public String getCounter() {
        logger.info("RequestCounterController");
        return RequestCounter.getCounter() + " requests were sent";
    }
}
