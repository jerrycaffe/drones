package com.musalasoft.drones.drones.service;

import com.musalasoft.drones.drones.dto.BatteryDetails;
import com.musalasoft.drones.drones.dto.DroneLoadReq;
import com.musalasoft.drones.drones.dto.DroneRegisterReq;
import com.musalasoft.drones.drones.model.Drone;

import com.musalasoft.drones.drones.model.Medication;

import java.util.List;

public interface DroneService {
    Drone register(DroneRegisterReq droneRegisterRequest);
    Medication load(DroneLoadReq droneLoadReq, Long droneId);
//    DroneCheckResp check(Long droneId);
    void checkAllBatteryLevels();
    BatteryDetails checkBattery(Long id);
    List<Drone> availableDrones();
    Drone getDroneById(Long droneId);
}
