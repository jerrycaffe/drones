package com.musalasoft.drones.drones.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduleService {
    private final DroneService droneService;
    @Scheduled(cron = "0 */3 * * * ?")  // Every three minute
    public void checkDroneBatteries() {
        droneService.checkAllBatteryLevels();
    }
}
