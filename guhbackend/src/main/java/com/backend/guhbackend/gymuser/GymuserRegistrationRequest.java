package com.backend.guhbackend.gymuser;

import java.time.LocalDate;
import java.util.HashMap;

public record GymuserRegistrationRequest(String name,
                                         String email,
                                         LocalDate dob,
                                         LocalDate registrationDate,
                                         HashMap<Integer, LocalDate> purchaseDateMap) {

}
