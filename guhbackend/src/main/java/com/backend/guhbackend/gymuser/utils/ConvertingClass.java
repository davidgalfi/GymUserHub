package com.backend.guhbackend.gymuser.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.LocalDate;
import java.util.HashMap;

@Converter
public class ConvertingClass implements AttributeConverter<HashMap<Integer, LocalDate>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Override
    public String convertToDatabaseColumn(HashMap<Integer, LocalDate> attribute) {
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
    public HashMap<Integer, LocalDate> convertToEntityAttribute(String dbData) {
        try {
            TypeReference<HashMap<Integer, LocalDate>> typeReference =
                    new TypeReference<HashMap<Integer, LocalDate>>() {};
            return objectMapper.readValue(dbData, typeReference);
        } catch (Exception e) {
            throw new RuntimeException("Error converting JSON to HashMap", e);
        }
    }
}

