package com.backend.guhbackend.gymworker.dto;

public record GymworkerLoginRequest(
        String email,
        String password
) {
}
