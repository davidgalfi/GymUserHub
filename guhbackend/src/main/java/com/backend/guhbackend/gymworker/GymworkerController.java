package com.backend.guhbackend.gymworker;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(path = "/get")
    public List<GymworkerDTO> getAllGymworkers(@RequestParam(required = false) Long id,
                                            @RequestParam(required = false) String email,
                                            @RequestParam(required = false) String phone) {
        return gymworkerService.getAllGymworkers(id, email, phone);
    }

    @PostMapping(path = "/create")
    public void registerGymworker(@RequestBody GymworkerRegistrationRequest gymworkerRegistrationRequest){
        gymworkerService.registerGymworker(gymworkerRegistrationRequest);
    }
}
