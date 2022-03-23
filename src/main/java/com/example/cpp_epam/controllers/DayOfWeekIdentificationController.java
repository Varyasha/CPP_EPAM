package com.example.cpp_epam.controllers;

import com.example.cpp_epam.entities.DayOfWeek;
import com.example.cpp_epam.services.DayOfWeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.logging.Logger;

@Validated
@RestController
public class DayOfWeekIdentificationController {
    private final Logger logger = Logger.getLogger(DayOfWeekIdentificationController.class.getName());

    @Autowired
    private DayOfWeekService dayOfWeekService;

    @GetMapping("/checkday")
    public DayOfWeek checkday(@RequestParam(value = "year", required = true) @Min(0) int year,
                              @RequestParam(value = "day", required = true) @Min(0) int day) {
        DayOfWeek result = dayOfWeekService.dayOfWeekResult(year, day);
        logger.info("Successful getMapping");
        return result;
    }
}
