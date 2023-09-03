package com.backend.guhbackend.gymworker;

public record GymworkerUpdateRequest(String name,
                                     String email,
                                     String password,
                                     String phone,
                                     String workingType){
}
