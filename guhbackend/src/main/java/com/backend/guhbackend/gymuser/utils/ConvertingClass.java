package com.backend.guhbackend.gymuser.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedHashMap;

@Converter
public class ConvertingClass implements AttributeConverter<LinkedHashMap<Integer, LocalDate>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Override
    public String convertToDatabaseColumn(LinkedHashMap<Integer, LocalDate> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new RuntimeException("Error converting HashMap to JSON", e);
        }
    }

    /*@Override
    public HashMap<Integer, LocalDate> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, HashMap.class);
        } catch (Exception e) {
            throw new RuntimeException("Error converting JSON to HashMap", e);
        }
    }*/

    @Override
    public LinkedHashMap<Integer, LocalDate> convertToEntityAttribute(String dbData) {
        try {
            TypeReference<LinkedHashMap<Integer, LocalDate>> typeReference =
                    new TypeReference<LinkedHashMap<Integer, LocalDate>>() {};
            return objectMapper.readValue(dbData, typeReference);
        } catch (Exception e) {
            throw new RuntimeException("Error converting JSON to HashMap", e);
        }
    }
}

