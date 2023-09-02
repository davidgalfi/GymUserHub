package com.backend.guhbackend.gymuser.utils;

import com.backend.guhbackend.gymuser.Gymuser;
import com.backend.guhbackend.gymuser.GymuserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DailyTaskRunner {
    private final GymuserRepository gymuserRepository;

    @Autowired
    public DailyTaskRunner(GymuserRepository gymuserRepository) {
        this.gymuserRepository = gymuserRepository;
    }

    public void TicketUpdater() {
        List<Gymuser> gymusers = gymuserRepository.findAll();

        for (Gymuser gymuser : gymusers) {
            gymuser.setDaysAllowed();
        }
    }
}
