package com.example.cpp_epam.controllers;

import com.example.cpp_epam.entities.DayOfWeek;
import com.example.cpp_epam.exceptions.BadRequestException;
import com.example.cpp_epam.exceptions.InternalServerErrorException;
import com.example.cpp_epam.services.DayOfWeekService;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.LogManager;
import java.util.logging.Logger;


@RestController
public class DayOfWeekIdentificationController {
    private final Logger logger = Logger.getLogger(DayOfWeekIdentificationController.class.getName());
    Handler consoleHandler = new ConsoleHandler();
    private final DayOfWeekService dayOfWeekService;
    public DayOfWeekIdentificationController(DayOfWeekService dayOfWeekService){
       this.dayOfWeekService = dayOfWeekService;
    }
    @GetMapping("/checkday")
    public DayOfWeek checkday(@RequestParam(value = "year", defaultValue = "0") int year,
                              @RequestParam(value = "day", defaultValue = "0") int day)
                              throws BadRequestException, InternalServerErrorException {
       String result = dayOfWeekService.DayOfWeekResult(year,day);
       logger.addHandler(consoleHandler);
       logger.info("Successful getMapping");
       return new DayOfWeek(result);
    }
}
