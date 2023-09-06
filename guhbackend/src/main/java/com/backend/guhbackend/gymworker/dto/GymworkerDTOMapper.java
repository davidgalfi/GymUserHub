package com.backend.guhbackend.gymworker.dto;

import com.backend.guhbackend.gymworker.Gymworker;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class GymworkerDTOMapper implements Function<Gymworker, GymworkerDTO> {

    @Override
    public GymworkerDTO apply(Gymworker input) {
        return new GymworkerDTO(
                input.getId(),
                input.getName(),
                input.getEmail(),
                input.getPhone(),
                input.getWorkingType(),
                input.getDob(),
                input.getStartedWork()
        );
    }
}
