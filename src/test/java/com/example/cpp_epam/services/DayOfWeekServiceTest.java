package com.example.cpp_epam.services;

import com.example.cpp_epam.exceptions.BadRequestException;
import com.example.cpp_epam.exceptions.InternalServerErrorException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DayOfWeekServiceTest {
    private final DayOfWeekService dayOfWeekService = new DayOfWeekService();
    @Test
    void TestDayOfWeekResult() throws BadRequestException, InternalServerErrorException {
        int year = 2022;
        int day = 2;
        String expected = "Воскресенье";
        assertEquals(expected, dayOfWeekService.DayOfWeekResult(year,day));
    }

    @Test
    void TestDayOfWeekResultException() throws BadRequestException, InternalServerErrorException {
        boolean testResult = false;
        int year = 2022;
        int day = 366;
        try{
            dayOfWeekService.DayOfWeekResult(year,day);
        }catch (BadRequestException exception){
            testResult = true;
        }
        assertTrue(testResult);
    }
}