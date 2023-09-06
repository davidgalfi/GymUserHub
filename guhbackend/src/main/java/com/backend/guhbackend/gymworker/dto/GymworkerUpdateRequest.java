package com.backend.guhbackend.gymworker.dto;

public record GymworkerUpdateRequest(String name,
                                     String email,
                                     String password,
                                     String phone,
                                     String workingType){
}
