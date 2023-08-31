package com.backend.guhbackend.gymuser;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.List;

@Configuration
public class GymuserConfig {

    @Bean
    CommandLineRunner commandLineRunner(GymuserRepository gymuserRepository){
        return args -> {
            HashMap<Integer, LocalDate> hashMap_1 = new HashMap<>();
            HashMap<Integer, LocalDate> hashMap_2 = new HashMap<>();
            hashMap_1.put(10,LocalDate.of(2023, Month.AUGUST, 26));
            hashMap_2.put(2,LocalDate.of(2023, Month.AUGUST, 30));
            /*String name,
                   String email,
                   LocalDate dob,
                   LocalDate registrationDate,
                   HashMap<Integer, LocalDate> purchaseDateMap*/
            Gymuser david = new Gymuser(
                    "David",
                    "david@gmail.com",
                    LocalDate.of(2002, Month.JUNE, 8),
                    LocalDate.of(2023, Month.AUGUST, 15),
                    hashMap_1
            );
            Gymuser alex = new Gymuser(
                    "Alex",
                    "alex@gmail.com",
                    LocalDate.of(2003, Month.JUNE, 23),
                    LocalDate.of(2023, Month.AUGUST, 20),
                    hashMap_2
            );
            gymuserRepository.saveAll(List.of(david, alex));
        };
    }
}
