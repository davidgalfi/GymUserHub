package com.backend.guhbackend.gymuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
