package com.musalasoft.drones.drones.controller;

import com.musalasoft.drones.drones.dto.BatteryDetails;
import com.musalasoft.drones.drones.dto.DroneLoadReq;
import com.musalasoft.drones.drones.dto.DroneRegisterReq;
import com.musalasoft.drones.drones.model.Drone;
import com.musalasoft.drones.drones.model.Medication;
import com.musalasoft.drones.drones.service.DroneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/drones")
@RequiredArgsConstructor
public class DroneController {
    private final DroneService droneService;
    @PostMapping
    public Drone registerDrone(@Valid @RequestBody DroneRegisterReq droneRegisterReq){
        return droneService.register(droneRegisterReq);
    }
    @PostMapping("/{droneId}")
    public Medication loadMedication(@Valid @RequestBody DroneLoadReq droneLoadReq, @PathVariable Long droneId){
        return droneService.load(droneLoadReq, droneId);
    }

    @GetMapping("/battery/{droneId}")
    public BatteryDetails checkBatteryStatus(
            @PathVariable Long droneId
    ){
        return droneService.checkBattery(droneId);
    }
}
