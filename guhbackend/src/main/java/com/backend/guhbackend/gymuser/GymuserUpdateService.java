package com.backend.guhbackend.gymuser;

import com.backend.guhbackend.gymuser.utils.DailyTaskRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class GymuserUpdateService {

    private final DailyTaskRunner dailyTaskRunner;

    @Autowired
    public GymuserUpdateService(DailyTaskRunner dailyTaskRunner) {
        this.dailyTaskRunner = dailyTaskRunner;
    }

    @Scheduled(cron = "0 * * * * *")
    public void runTicketUpdater() {
        dailyTaskRunner.TicketUpdater();
    }

}
