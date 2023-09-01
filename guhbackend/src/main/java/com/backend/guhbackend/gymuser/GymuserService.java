package com.backend.guhbackend.gymuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public void registerGymUser(GymuserRegistrationRequest gymuserRegistrationRequest) throws GymuserAlreadyRegisteredException {
        Optional<Gymuser> gymuserOptional = gymuserRepository.
                findGymuserByEmail(gymuserRegistrationRequest.email());
        if(gymuserOptional.isPresent()){
            throw new GymuserAlreadyRegisteredException("Email already registered!");
        }
        Gymuser gymuser = new Gymuser(
                gymuserRegistrationRequest.name(),
                gymuserRegistrationRequest.email(),
                gymuserRegistrationRequest.dob(),
                gymuserRegistrationRequest.registrationDate(),
                gymuserRegistrationRequest.purchaseDateMap());

        gymuserRepository.save(gymuser);
    }
}
