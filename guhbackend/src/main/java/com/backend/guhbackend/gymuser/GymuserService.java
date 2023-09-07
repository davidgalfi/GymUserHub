package com.backend.guhbackend.gymuser;

import com.backend.guhbackend.exception.GymuserAlreadyRegisteredException;
import com.backend.guhbackend.exception.InvalidTicketDateException;
import com.backend.guhbackend.gymuser.dto.GymuserDTO;
import com.backend.guhbackend.gymuser.dto.GymuserDTOMapper;
import com.backend.guhbackend.gymuser.dto.GymuserRegistrationRequest;
import com.backend.guhbackend.gymuser.dto.GymuserUpdateRequest;
import com.backend.guhbackend.utils.ArithmeticUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
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

    public List<GymuserDTO> getGymUsers(Long id, String email){
        if(id != null){
            Optional<Gymuser> gymuserOptional = gymuserRepository.findById(id);
            if(gymuserOptional.isEmpty()){
                throw new IllegalStateException("Gymuser with " + id + " not found");
            }
            return Collections.singletonList(gymuserDTOMapper.apply(gymuserOptional.get()));
        }

        if(email != null){
            Optional<Gymuser> gymuserOptional = gymuserRepository.findGymuserByEmail(email);
            if(gymuserOptional.isEmpty()){
                throw new IllegalStateException("Gymuser with " + email + " not found");
            }
            return Collections.singletonList(gymuserDTOMapper.apply(gymuserOptional.get()));
        }

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

    public void deleteGymUser(Long id) {
        Optional<Gymuser> gymuserOptional = gymuserRepository.findById(id);
        if(gymuserOptional.isPresent()){
            Gymuser gymuser = gymuserOptional.get();
            gymuserRepository.delete(gymuser);
        };
    }

    @Transactional
    public void updateGymUser(Long id, GymuserUpdateRequest gymuserUpdateRequest)
            throws GymuserAlreadyRegisteredException, InvalidTicketDateException {
        Gymuser gymuser = gymuserRepository
                .findById(id)
                .orElseThrow(() -> new IllegalStateException("Gymuser with " + id + " not found"));

        if(gymuserUpdateRequest.name()!= null &&
        gymuserUpdateRequest.name().length() > 0 &&
        !Objects.equals(gymuser.getName(), gymuserUpdateRequest.name())){
            gymuser.setName(gymuserUpdateRequest.name());
        }

        if(gymuserUpdateRequest.email()!= null &&
        gymuserUpdateRequest.email().length() > 0 &&
       !Objects.equals(gymuser.getEmail(), gymuserUpdateRequest.email())){
            Optional<Gymuser> gymuserOptional = gymuserRepository.findGymuserByEmail(gymuserUpdateRequest.email());
            if(gymuserOptional.isPresent()){
                // TODO: Resolve error message problem
                throw new GymuserAlreadyRegisteredException("Email already registered!");
            }
            gymuser.setEmail(gymuserUpdateRequest.email());
        }

        if(gymuserUpdateRequest.purchaseDateMap()!= null &&
        gymuserUpdateRequest.purchaseDateMap().size() > 0){
            if(ArithmeticUtils.daysAllowedToUseGym(gymuserUpdateRequest.purchaseDateMap()) == 0){
                throw new InvalidTicketDateException("Invalid ticket date. Please enter a date that allows more than 0 days.");
            }
            gymuser.setPurchaseDateMap(gymuserUpdateRequest.purchaseDateMap());
        }
    }
}
