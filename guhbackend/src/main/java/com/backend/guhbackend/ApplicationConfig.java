package com.backend.guhbackend;

import com.backend.guhbackend.gymuser.Gymuser;
import com.backend.guhbackend.gymuser.GymuserRepository;
import com.backend.guhbackend.gymworker.Gymworker;
import com.backend.guhbackend.gymworker.GymworkerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedHashMap;
import java.util.List;

@Configuration
public class ApplicationConfig {

    @Bean
    CommandLineRunner commandLineRunner(GymuserRepository gymuserRepository, GymworkerRepository gymworkerRepository){
        return args -> {
            LinkedHashMap<Integer, LocalDate> linkedhashMap_1 = new LinkedHashMap<>();
            LinkedHashMap<Integer, LocalDate> linkedhashMap_2 = new LinkedHashMap<>();
            linkedhashMap_1.put(10,LocalDate.of(2023, Month.AUGUST, 26));
            linkedhashMap_2.put(2,LocalDate.of(2023, Month.AUGUST, 30));
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
                    linkedhashMap_1
            );
            Gymuser alex = new Gymuser(
                    "Alex",
                    "alex@gmail.com",
                    LocalDate.of(2003, Month.JUNE, 23),
                    LocalDate.of(2023, Month.AUGUST, 20),
                    linkedhashMap_2
            );
            gymuserRepository.saveAll(List.of(david, alex));

            /*private Long id;
            private String name;
            private String password;
            private String email;
            private String phone;
            private String workingType;
            private LocalDate dob;
            private LocalDate startedWork;
            private Integer age;
            private Integer workingTime;*/

            Gymworker gymworker_test_1 = new Gymworker(
                    "Test_1",
                    "123",
                    "test1@gmail.com",
                    "06202223333",
                    "Full-time",
                    LocalDate.of(2000, 1, 1),
                    LocalDate.of(2020, 1, 1)
            );
            Gymworker gymworker_test_2 = new Gymworker(
                    "Test_2",
                    "123",
                    "test2@gmail.com",
                    "06204445555",
                    "Part-time",
                    LocalDate.of(2001, 2, 3),
                    LocalDate.of(2022, 1, 1)
            );
            gymworkerRepository.saveAll(List.of(gymworker_test_1, gymworker_test_2));
        };
    }
}
