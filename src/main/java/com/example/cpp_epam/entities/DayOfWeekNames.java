package com.example.cpp_epam.entities;

public enum DayOfWeekNames {
    MANDAY("понедельник"),
    TUESDAY("вторник"),
    WEDNESDAY("среда"),
    THURSDAY("четверг"),
    FRIDAY("пятница"),
    SATURDAY("суббота"),
    SUNDAY("воскресенье");

    private final String name;

    DayOfWeekNames(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
