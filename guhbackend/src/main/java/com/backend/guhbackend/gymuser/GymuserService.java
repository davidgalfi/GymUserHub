package com.backend.guhbackend.gymuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GymuserService {

    private final GymuserRepository gymuserRepository;

    @Autowired
    public GymuserService(GymuserRepository gymuserRepository){
        this.gymuserRepository = gymuserRepository;
    }

    public List<GymuserDTO> getGymUsers(){
        return gymuserRepository.findAll()
                .stream()
                .map(gymuser -> new GymuserDTO(
                        gymuser.getId(),
                        gymuser.getName(),
                        gymuser.getEmail(),
                        gymuser.getDob(),
                        gymuser.getRegistrationDate(),
                        gymuser.getPurchaseDateMap(),
                        gymuser.getDaysAllowed(),
                        gymuser.getAge()
                )).collect(Collectors.toList());
    }
}
