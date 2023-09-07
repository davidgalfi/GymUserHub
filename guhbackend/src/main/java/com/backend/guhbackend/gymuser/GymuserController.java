package com.backend.guhbackend.gymuser;

import com.backend.guhbackend.exception.GymuserAlreadyRegisteredException;
import com.backend.guhbackend.exception.InvalidTicketDateException;
import com.backend.guhbackend.gymuser.dto.GymuserDTO;
import com.backend.guhbackend.gymuser.dto.GymuserRegistrationRequest;
import com.backend.guhbackend.gymuser.dto.GymuserUpdateRequest;
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

    @GetMapping()
    public List<GymuserDTO> getGymUsers(@RequestParam(required = false) Long id, @RequestParam(required = false) String email){
        return gymuserService.getGymUsers(id, email);
    }

    @PostMapping()
    public void registerGymUser(@RequestBody GymuserRegistrationRequest gymuserRegistrationRequest) throws GymuserAlreadyRegisteredException {
        gymuserService.registerGymUser(gymuserRegistrationRequest);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteGymUser(@PathVariable Long id){
        gymuserService.deleteGymUser(id);
    }

    @PutMapping(path = "/{id}")
    public void updateGymUser(@PathVariable Long id, @RequestBody GymuserUpdateRequest gymuserUpdateRequest)
            throws GymuserAlreadyRegisteredException, InvalidTicketDateException {
        gymuserService.updateGymUser(id, gymuserUpdateRequest);
    }
}
