package com.backend.guhbackend.gymuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GymuserService {

    private final GymuserRepository gymuserRepository;
    private final GymuserDTOMapper gymuserDTOMapper;

    @Autowired
    public GymuserService(GymuserRepository gymuserRepository,
                          GymuserDTOMapper gymuserDTOMapper){
        this.gymuserRepository = gymuserRepository;
        this.gymuserDTOMapper = gymuserDTOMapper;
    }

    public List<GymuserDTO> getGymUsers(){
        return gymuserRepository.findAll()
                .stream()
                .map(gymuserDTOMapper).collect(Collectors.toList());
    }
}
