package com.backend.guhbackend.gymworker;

public record GymworkerLoginRequest(
        String email,
        String password
) {
}
