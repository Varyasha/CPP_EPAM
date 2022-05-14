package com.example.cpp_epam.controllers;

import com.example.cpp_epam.entities.DayOfWeek;
import com.example.cpp_epam.services.DayOfWeekCache;
import com.example.cpp_epam.services.DayOfWeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
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

    @GetMapping("/checkdayCache")
    public DayOfWeekCache printLinkedHashMap(){
        return dayOfWeekService.getLinkedHashMap();
    }

    @PostMapping("/checkday")
    public ResponseEntity<?> bulkOperations(@Valid @RequestBody List<LocalDate> dates){
        LinkedList<DayOfWeek> results = new LinkedList<>();
        dates.forEach((currentElement) -> {
            try {
                results.add(dayOfWeekService.dayOfWeekResult(currentElement.getYear(), currentElement.getDayOfYear()));
            } catch (Exception e) {
                logger.log(Level.WARNING,"Error in postMapping");
            }
        });
        LocalDate min, max;
        min = dates.stream()
                .min(Comparator.comparing(LocalDate::toEpochDay))
                .get();
        logger.info("Min = " + min);
        max = dates.stream()
                .max(Comparator.comparing(LocalDate::toEpochDay))
                .get();

        logger.info("Max = " + max);
        logger.info("Successful postMapping");
        return new ResponseEntity<>(results + "\nMax day: " + max + "\nMin day: " + min, HttpStatus.OK);
    }
}
