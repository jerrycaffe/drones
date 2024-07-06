package com.musalasoft.drones.drones.repository;

import com.musalasoft.drones.drones.model.Drone;
import com.musalasoft.drones.drones.model.DroneStateEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DroneRepository extends JpaRepository<Drone, Long> {
    List<Drone> findAllByBatteryCapacityGreaterThanAndState(int batteryLevel, DroneStateEnum stateEnum);
}
