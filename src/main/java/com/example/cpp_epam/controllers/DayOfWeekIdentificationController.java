package com.example.cpp_epam.controllers;

import com.example.cpp_epam.entities.DayOfWeek;
import com.example.cpp_epam.entities.ResultDto;
import com.example.cpp_epam.services.DayOfWeekCache;
import com.example.cpp_epam.services.DayOfWeekService;
import com.example.cpp_epam.utility.LocalDateConverter;
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
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.stream.Collectors.toList;

@Validated
@RestController
public class DayOfWeekIdentificationController {
    private final Logger logger = Logger.getLogger(DayOfWeekIdentificationController.class.getName());

    @Autowired
    private DayOfWeekService dayOfWeekService;

    @Autowired
    private LocalDateConverter converter;

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
    public ResponseEntity<?> bulkOperations(@Valid @RequestBody List<String> dates) {
        if (dates.isEmpty()) {
            return new ResponseEntity<>(new ResultDto(), HttpStatus.OK);
        }
        LinkedList<DayOfWeek> results = new LinkedList<>();
        List<LocalDate> localDates = dates.stream().map(v -> converter.convert(v)).collect(toList());
        localDates.forEach((currentElement) -> {
            try {
                results.add(dayOfWeekService.dayOfWeekResult(currentElement.getYear(), currentElement.getDayOfYear()));
            } catch (Exception e) {
                logger.log(Level.WARNING, "Error in postMapping");
            }
        });
        ResultDto dto = new ResultDto();
        Optional<LocalDate> min = localDates.stream().min(Comparator.comparing(LocalDate::toEpochDay));
        Optional<LocalDate> max = localDates.stream().max(Comparator.comparing(LocalDate::toEpochDay));
        if (min.isPresent()) {
            logger.info("Min = " + min.get());
            dto.setMinDate(min.get());
        }
        if (max.isPresent()) {
            logger.info("Max = " + max.get());
            dto.setMaxDate(max.get());
        }
        dto.setResults(results);
        logger.info("Successful postMapping");
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
