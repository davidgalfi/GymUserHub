package com.backend.guhbackend.gymuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/costumers")
public class GymuserController {

    private final GymuserService gymuserService;

    @Autowired
    public GymuserController(GymuserService gymuserService) {
        this.gymuserService = gymuserService;
    }

    @GetMapping(path = "/get")
    public List<GymuserDTO> getGymUsers(){
        return gymuserService.getGymUsers();
    }

    @PostMapping(path = "/create")
    public void registerGymUser(@RequestBody GymuserRegistrationRequest gymuserRegistrationRequest) throws GymuserAlreadyRegisteredException {
        gymuserService.registerGymUser(gymuserRegistrationRequest);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void deleteGymUser(@PathVariable Long id){
        gymuserService.deleteGymUser(id);
    }

    @PutMapping(path = "/update/{id}")
    public void updateGymUser(@PathVariable Long id, @RequestBody GymuserUpdateRequest gymuserUpdateRequest)
            throws GymuserAlreadyRegisteredException, InvalidTicketDateException {
        gymuserService.updateGymUser(id, gymuserUpdateRequest);
    }
}
