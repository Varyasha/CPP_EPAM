package com.example.cpp_epam.services;

import com.example.cpp_epam.counter.RequestCounterThread;
import com.example.cpp_epam.entities.DayOfWeek;
import com.example.cpp_epam.entities.DayOfWeekNames;
import com.example.cpp_epam.exceptions.DayNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class DayOfWeekService {
    private static final Logger logger = Logger.getLogger(DayOfWeekService.class.getName());

    @Autowired
    private DayOfWeekCache linkedHashMap;

    public DayOfWeek dayOfWeekResult(int year, int day) {
        new RequestCounterThread(Thread.currentThread().getName()).start();
        LocalDate weekDay;
        DayOfWeekNames result;
        try {
            weekDay = LocalDate.ofYearDay(year, day);
        } catch (DateTimeException exception) {
            logger.log(Level.WARNING, "ERROR 500! Day does not exist");
            throw new DayNotExistException("Day does not exist");
        }
        if (linkedHashMap.findByKey(weekDay)) {
            result = linkedHashMap.getParameters(weekDay);
            logger.info("The result is taken from linkedHashMap");
        } else {
            result = DayOfWeekNames.valueOf(weekDay.getDayOfWeek().name());
            linkedHashMap.addToLinkedHashMap(weekDay, result);
            logger.info("Added to linkedHashMap");
        }
        return new DayOfWeek(result.getName());
    }

    public DayOfWeekCache getLinkedHashMap() {
        return linkedHashMap;
    }
}
