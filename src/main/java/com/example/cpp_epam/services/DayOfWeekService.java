package com.example.cpp_epam.services;

import com.example.cpp_epam.entities.DayOfWeek;
import com.example.cpp_epam.entities.DayOfWeekNames;
import com.example.cpp_epam.exceptions.DayNotExistException;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class DayOfWeekService {
    private static final Logger logger = Logger.getLogger(DayOfWeekService.class.getName());

    public DayOfWeek dayOfWeekResult(int year, int day) {
        LocalDate weekDay;
        try {
            weekDay = LocalDate.ofYearDay(year, day);
        } catch (DateTimeException exception) {
            logger.log(Level.WARNING, "ERROR 500! Day does not exist");
            throw new DayNotExistException("Day does not exist");
        }
        DayOfWeekNames result = DayOfWeekNames.valueOf(weekDay.getDayOfWeek().name());
        return new DayOfWeek(result.getName());
    }
}
