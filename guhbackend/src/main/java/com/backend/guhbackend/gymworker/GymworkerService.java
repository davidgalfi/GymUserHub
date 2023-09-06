package com.backend.guhbackend.gymworker;

import com.backend.guhbackend.gymworker.dto.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GymworkerService {

    private final GymworkerRepository gymworkerRepository;
    private final GymworkerDTOMapper gymworkerDTOMapper;

    @Autowired
    public GymworkerService(GymworkerRepository gymworkerRepository, GymworkerDTOMapper gymworkerDTOMapper) {
        this.gymworkerRepository = gymworkerRepository;
        this.gymworkerDTOMapper = gymworkerDTOMapper;
    }

    public List<GymworkerDTO> getAllGymworkers(Long id, String email, String phone) {
        if(id != null){
            Optional<Gymworker> worker = gymworkerRepository.findById(id);
            if(worker.isEmpty()){
                throw new IllegalStateException("Gym-worker with: " + id + " not found!");
            }
            return Collections.singletonList(gymworkerDTOMapper.apply(worker.get()));
        }

        if(email != null){
            Optional<Gymworker> worker = gymworkerRepository.findGymworkerByEmail(email);
            if(worker.isEmpty()){
                throw new IllegalStateException("Gym-worker with: " + email + " not found!");
            }
            return Collections.singletonList(gymworkerDTOMapper.apply(worker.get()));
        }

        if(phone != null){
            Optional<Gymworker> worker = gymworkerRepository.findGymworkerByPhone(phone);
            if(worker.isEmpty()){
                throw new IllegalStateException("Gym-worker with: " + phone + " not found!");
            }
            return Collections.singletonList(gymworkerDTOMapper.apply(worker.get()));
        }

        return gymworkerRepository.findAll()
                .stream()
                .map(gymworkerDTOMapper)
                .collect(Collectors.toList());
    }

    public void registerGymworker(GymworkerRegistrationRequest gymworkerRegistrationRequest) {
        Optional<Gymworker> gymworkerOptional =
                gymworkerRepository.findGymworkerByEmail(gymworkerRegistrationRequest.email());

        if(gymworkerOptional.isPresent()) {
            throw new IllegalStateException("Gym-worker with: " + gymworkerRegistrationRequest.email() + " already exists!");
        }

        Gymworker gymworker = new Gymworker(
                gymworkerRegistrationRequest.name(),
                gymworkerRegistrationRequest.password(),
                gymworkerRegistrationRequest.email(),
                gymworkerRegistrationRequest.phone(),
                gymworkerRegistrationRequest.workingType(),
                gymworkerRegistrationRequest.dob(),
                gymworkerRegistrationRequest.startedWork());

        gymworkerRepository.save(gymworker);
    }

    public void deleteGymworker(Long id, String email) {
        if(id == null && email == null){
            throw new IllegalStateException("No id or email provided!");
        }
        if(id != null){
            Optional<Gymworker> worker = gymworkerRepository.findById(id);
            if(worker.isEmpty()){
                throw new IllegalStateException("Gym-worker with: " + id + " not found!");
            }
            gymworkerRepository.delete(worker.get());
        }

        if(email!= null){
            Optional<Gymworker> worker = gymworkerRepository.findGymworkerByEmail(email);
            if(worker.isEmpty()){
                throw new IllegalStateException("Gym-worker with: " + email + " not found!");
            }
            gymworkerRepository.delete(worker.get());
        }
    }

    @Transactional
    public void updateGymworker(Long id, String email, GymworkerUpdateRequest gymworkerUpdateRequest) {
        Gymworker gymworker = new Gymworker();

        if(email != null){
            gymworker = gymworkerRepository.findGymworkerByEmail(email)
                    .orElseThrow(() -> new IllegalStateException("Could not find worker with email: " + email));
        } else if(id != null){
            gymworker = gymworkerRepository.findById(id)
                   .orElseThrow(() -> new IllegalStateException("Could not find worker with id: " + id));
        } else {
            throw new IllegalStateException("No id or email provided!");
        }

        if(gymworkerUpdateRequest.name() != null
                && gymworkerUpdateRequest.name().length() > 0) {
            gymworker.setName(gymworkerUpdateRequest.name());
        }

        if(gymworkerUpdateRequest.email() != null
                && gymworkerUpdateRequest.email().length() > 0) {
            Optional<Gymworker> gymworkerOptional = gymworkerRepository
                    .findGymworkerByEmail(gymworkerUpdateRequest.email());
            if(gymworkerOptional.isPresent()){
                throw new IllegalStateException("Email already registered!");
            }
            gymworker.setEmail(gymworkerUpdateRequest.email());
        }

        if(gymworkerUpdateRequest.password() != null
                && gymworkerUpdateRequest.password().length() > 0) {
            gymworker.setPassword(gymworkerUpdateRequest.password());
        }

        if(gymworkerUpdateRequest.phone() != null
                && gymworkerUpdateRequest.phone().length() > 0) {
            gymworker.setPhone(gymworkerUpdateRequest.phone());
        }

        if(gymworkerUpdateRequest.workingType() != null
                && gymworkerUpdateRequest.workingType().length() > 0) {
            gymworker.setWorkingType(gymworkerUpdateRequest.workingType());
        }
    }

    public ResponseEntity<String> login(GymworkerLoginRequest gymworkerLoginRequest) {
        Optional<Gymworker> gymworkerOptional =
                gymworkerRepository.findGymworkerByEmail(gymworkerLoginRequest.email());

        if(gymworkerOptional.isPresent()) {
            Gymworker gymworker = gymworkerOptional.get();
            if(gymworker.getPassword().equals(gymworkerLoginRequest.password())) {
                return ResponseEntity.ok(gymworker.getEmail());
            }
        }

        return ResponseEntity.badRequest().build();
    }
}
