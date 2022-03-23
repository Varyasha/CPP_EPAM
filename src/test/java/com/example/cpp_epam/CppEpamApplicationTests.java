package com.example.cpp_epam;

import com.example.cpp_epam.controllers.DayOfWeekIdentificationController;
import com.example.cpp_epam.entities.DayOfWeek;
import com.example.cpp_epam.exceptions.DayNotExistException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CppEpamApplicationTests {

    @Autowired
    DayOfWeekIdentificationController controller;

    @Autowired
    private TestRestTemplate restTemplate;

    //тест на правильное определение дня недели
    @Test
    public void checkCorrectDay() {
        int year = 2022;
        int day = 2;
        DayOfWeek result = controller.checkday(year, day);
        assertEquals("воскресенье", result.getDayName());
    }

    //тест на недостающий параметр
    @Test
    public void missingParam() {
        String actual = restTemplate.getForObject("http://localhost:8081/checkday?year=2020", String.class);
        String excepted = "{\"message\":\"Required request parameter 'day' for method parameter type int is not present\"" + ",\"code\":400}";
        System.out.println(actual);
        assertEquals(excepted, actual);
    }

    //тест на отрицательный параметр
    @Test
    public void negativeParam() {
        String actual = restTemplate.getForObject("http://localhost:8081/checkday?year=2022&day=-1", String.class);
        String excepted = "{\"message\":\"checkday.day: must be greater than or equal to 0\",\"code\":400}";
        System.out.println(actual);
        assertEquals(excepted, actual);
    }

    //тесты на несуществующий день
    @Test(expected = DayNotExistException.class)
    public void dayNotExist_firstApproach() throws DayNotExistException {
        controller.checkday(2022, 366);
    }

    @Test
    public void dayNotExist_secondApproach() {
        String actual = restTemplate.getForObject("http://localhost:8081/checkday?year=2022&day=366", String.class);
        String excepted = "{\"message\":\"Day does not exist\",\"code\":500}";
        assertEquals(excepted, actual);
    }
}
