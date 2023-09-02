package com.backend.guhbackend.gymworker;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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
}
