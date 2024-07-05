package com.musalasoft.drones.drones.service;

import com.musalasoft.drones.drones.dto.DroneLoadReq;
import com.musalasoft.drones.drones.dto.DroneRegisterReq;
import com.musalasoft.drones.drones.exception.BadRequestException;
import com.musalasoft.drones.drones.exception.NotFoundException;
import com.musalasoft.drones.drones.model.Drone;
import com.musalasoft.drones.drones.model.DroneStateEnum;
import com.musalasoft.drones.drones.model.Medication;
import com.musalasoft.drones.drones.repository.DroneRepository;
import com.musalasoft.drones.drones.repository.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class DroneServiceImpl implements DroneService {
    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;
    private final String DRONE_NOT_FOUND = "Drone does not exist";

    @Override
    public Drone register(DroneRegisterReq droneRegisterRequest) {
        Drone drone = new Drone();
        drone.setWeightLimit(droneRegisterRequest.getWeightLimit());
        drone.setModel(droneRegisterRequest.getModel());
        drone.setBatteryCapacity(100);
        drone.setSerialNumber(droneRegisterRequest.getSerialNumber());
        drone.setState(DroneStateEnum.IDLE);

        return droneRepository.save(drone);
    }

    @Override
    public Medication load(DroneLoadReq droneLoadReq, Long droneId) {

        Drone drone = droneRepository.findById(droneId).orElseThrow(()-> new NotFoundException(DRONE_NOT_FOUND));
        List<Medication> allMedication = drone.getMedications() == null ? new ArrayList<Medication>() : drone.getMedications();

        BigDecimal totalWeight = sumMedicationWeight(allMedication);
        if (totalWeight.add(droneLoadReq.getWeight()).compareTo(drone.getWeightLimit()) > 0)
            throw new BadRequestException("Total weight exceeds drone's weight limit");
        Medication medication = new Medication();
        medication.setCode(droneLoadReq.getCode());
        medication.setName(droneLoadReq.getName());
        medication.setWeight(droneLoadReq.getWeight());
        medication.setImage(droneLoadReq.getImageUrl());
        medication.setDrone(drone);

        return medicationRepository.save(medication);
    }

    private BigDecimal sumMedicationWeight(List<Medication> medicationList) {
        return medicationList.isEmpty() ? BigDecimal.ZERO : medicationList.stream().map(Medication::getWeight).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
