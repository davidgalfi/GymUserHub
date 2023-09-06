package com.backend.guhbackend.gymuser.dto;

import com.backend.guhbackend.gymuser.Gymuser;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class GymuserDTOMapper implements Function<Gymuser, GymuserDTO> {

    @Override
    public GymuserDTO apply(Gymuser gymuser) {
        return new GymuserDTO(
                gymuser.getId(),
                gymuser.getName(),
                gymuser.getEmail(),
                gymuser.getDob(),
                gymuser.getRegistrationDate(),
                gymuser.getPurchaseDateMap());
    }
}
