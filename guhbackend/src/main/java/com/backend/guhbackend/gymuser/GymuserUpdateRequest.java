package com.backend.guhbackend.gymuser;

import java.time.LocalDate;
import java.util.LinkedHashMap;

public record GymuserUpdateRequest(String name,
                                   String email,
                                   LocalDate dob,
                                   LocalDate registrationDate,
                                   LinkedHashMap<Integer, LocalDate> purchaseDateMap) {
}
