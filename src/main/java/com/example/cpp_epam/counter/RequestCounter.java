package com.example.cpp_epam.counter;

public class RequestCounter {
    private static int counter;

    public static void increment() {
        ++counter;
    }

    public static Integer getCounter() {
        return counter;
    }
}
