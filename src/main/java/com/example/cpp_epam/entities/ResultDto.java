package com.example.cpp_epam.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.LinkedList;

public class ResultDto {

    @JsonProperty
    private LocalDate maxDate;
    @JsonProperty
    private LocalDate minDate;
    @JsonProperty
    private LinkedList<DayOfWeek> results;

    public void setMaxDate(LocalDate maxDate) {
        this.maxDate = maxDate;
    }

    public void setMinDate(LocalDate minDate) {
        this.minDate = minDate;
    }

    public void setResults(LinkedList<DayOfWeek> results) {
        this.results = results;
    }
}
