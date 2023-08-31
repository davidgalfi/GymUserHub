package com.backend.guhbackend.gymuser.utils;


import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;

public class ArithmeticUtils {

    public static Integer daysAllowedToUseGym(HashMap<Integer, LocalDate> map){
        LocalDate registeredTime = (LocalDate) CollectionFunctions.getLastEntryValue(map);
        Integer registeredDays = (Integer) CollectionFunctions.getLastEntryKey(map);
        if(registeredDays != null && registeredTime != null){
            Integer timeElapsed = Period.between(registeredTime, LocalDate.now()).getDays();
            return registeredDays - timeElapsed;
        } else {
            return 0;
        }
    }

    public static Integer ageCalculate(LocalDate dob){
        return Period.between(dob, LocalDate.now()).getYears();
    }
}
