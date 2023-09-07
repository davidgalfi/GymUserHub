package com.backend.guhbackend.gymworker;

import com.backend.guhbackend.gymworker.dto.GymworkerDTO;
import com.backend.guhbackend.gymworker.dto.GymworkerLoginRequest;
import com.backend.guhbackend.gymworker.dto.GymworkerRegistrationRequest;
import com.backend.guhbackend.gymworker.dto.GymworkerUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gymworker")
public class GymworkerController {

    private final GymworkerService gymworkerService;

    @Autowired
    public GymworkerController(GymworkerService gymworkerService) {
        this.gymworkerService = gymworkerService;
    }

    @GetMapping()
    public List<GymworkerDTO> getAllGymworkers(@RequestParam(required = false) Long id,
                                               @RequestParam(required = false) String email,
                                               @RequestParam(required = false) String phone) {
        return gymworkerService.getAllGymworkers(id, email, phone);
    }

    @PostMapping()
    public void registerGymworker(@RequestBody GymworkerRegistrationRequest gymworkerRegistrationRequest){
        gymworkerService.registerGymworker(gymworkerRegistrationRequest);
    }

    @DeleteMapping()
    public void deleteGymworker(@RequestParam(required = false) Long id,
                                @RequestParam(required = false) String name,
                                @RequestParam(required = false) String email) {
        gymworkerService.deleteGymworker(id, email);
    }

    @PutMapping()
    public void updateGymworker(@RequestParam(required = false) Long id,
                                @RequestParam(required = false) String email,
                                @RequestBody GymworkerUpdateRequest gymworkerUpdateRequest){
        gymworkerService.updateGymworker(id, email, gymworkerUpdateRequest);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody GymworkerLoginRequest gymworkerLoginRequest){
        return gymworkerService.login(gymworkerLoginRequest);
    }
}
