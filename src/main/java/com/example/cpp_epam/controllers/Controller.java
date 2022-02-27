package com.example.cpp_epam.controllers;

import com.example.cpp_epam.entities.DayOfWeek;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class Controller {
    @GetMapping("/greeting")
    public DayOfWeek greeting(@RequestParam(value = "year", defaultValue = "0") String year,
                            @RequestParam(value = "day", defaultValue = "0") String day) {
        LocalDate weekDay = LocalDate.ofYearDay(Integer.parseInt(year), Integer.parseInt(day));
        String result = weekDay.getDayOfWeek().toString();
        return new DayOfWeek(result);
    }
}
