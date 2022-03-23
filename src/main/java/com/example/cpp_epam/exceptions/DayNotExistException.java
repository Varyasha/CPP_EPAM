package com.example.cpp_epam.exceptions;

public class DayNotExistException extends RuntimeException {

    public DayNotExistException(String message) {
        super(message);
    }

    public DayNotExistException(Throwable cause) {
        super(cause);
    }

    public DayNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}