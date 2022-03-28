package com.example.cpp_epam.services;

import com.example.cpp_epam.entities.DayOfWeekNames;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class DayOfWeekCache {
    @JsonProperty
    private final Map<LocalDate, DayOfWeekNames> linkedHashMap = new LinkedHashMap<>();

    public boolean isContain(LocalDate key) {
        return linkedHashMap.containsKey(key);
    }

    public void addToLinkedHashMap(LocalDate key, DayOfWeekNames result) {
        linkedHashMap.put(key, result);
    }

    public DayOfWeekNames getParameters(LocalDate key) {
        return linkedHashMap.get(key);
    }
}
