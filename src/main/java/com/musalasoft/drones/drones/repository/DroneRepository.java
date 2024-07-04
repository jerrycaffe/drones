package com.musalasoft.drones.drones.repository;

import com.musalasoft.drones.drones.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DroneRepository extends JpaRepository<Drone, Long> {
}
