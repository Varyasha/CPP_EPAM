package com.example.cpp_epam.services;

import com.example.cpp_epam.exceptions.BadRequestException;
import com.example.cpp_epam.exceptions.InternalServerErrorException;

import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class DayOfWeekService {
    private static final Logger logger = Logger.getLogger(DayOfWeekService.class.getName());
    Handler consoleHandler = new ConsoleHandler();
    public String DayOfWeekResult(int year, int day) throws BadRequestException, InternalServerErrorException {
        logger.addHandler(consoleHandler);
        LocalDate weekDay;
        try {
            weekDay = LocalDate.ofYearDay(year, day);
        }catch (DateTimeException exception){
            logger.log(Level.WARNING,"ERROR 400! Incorrect request");
            throw new BadRequestException("Incorrect request");
        }
        String result = weekDay.getDayOfWeek().toString();
        switch(result){
            case "MONDAY": result = "Понедельник"; break;
            case "TUESDAY": result = "Вторник"; break;
            case "WEDNESDAY": result = "Среда"; break;
            case "THURSDAY": result = "Четверг"; break;
            case "FRIDAY": result = "Пятница"; break;
            case "SATURDAY": result = "Суббота"; break;
            case "SUNDAY": result = "Воскресенье"; break;
            default:
                logger.log(Level.WARNING,"ERROR 500! Error during the execution");
                throw new InternalServerErrorException("Error during the execution");
        }
        return result;
    }
}
