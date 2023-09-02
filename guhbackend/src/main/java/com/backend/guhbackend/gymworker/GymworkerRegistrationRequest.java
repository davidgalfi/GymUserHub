package com.backend.guhbackend.gymworker;

import java.time.LocalDate;

public record GymworkerRegistrationRequest(
        String name,
        String password,
        String email,
        String phone,
        String workingType,
        LocalDate dob,
        LocalDate startedWork
) {
}
