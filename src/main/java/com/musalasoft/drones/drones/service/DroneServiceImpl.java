package com.musalasoft.drones.drones.service;

import com.musalasoft.drones.drones.dto.BatteryDetails;
import com.musalasoft.drones.drones.dto.DroneLoadReq;
import com.musalasoft.drones.drones.dto.DroneRegisterReq;
import com.musalasoft.drones.drones.exception.BadRequestException;
import com.musalasoft.drones.drones.exception.NotFoundException;
import com.musalasoft.drones.drones.model.AuditLog;
import com.musalasoft.drones.drones.model.Drone;
import com.musalasoft.drones.drones.model.DroneStateEnum;
import com.musalasoft.drones.drones.model.Medication;
import com.musalasoft.drones.drones.repository.AuditLogRepository;
import com.musalasoft.drones.drones.repository.DroneRepository;
import com.musalasoft.drones.drones.repository.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class DroneServiceImpl implements DroneService {
    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;
    private final AuditLogRepository auditLogRepository;

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

        Drone drone = getDroneById(droneId);
        List<Medication> allMedication = drone.getMedications();

        if (allMedication.isEmpty()) drone.setState(DroneStateEnum.LOADING);


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

    @Override
    public Drone getDroneById(Long id) {
        return droneRepository.findById(id).orElseThrow(() -> new NotFoundException(DRONE_NOT_FOUND));
    }

    @Override
    public BatteryDetails checkBattery(Long id) {
        Drone drone = getDroneById(id);
        BatteryDetails batteryRes = new BatteryDetails();
        batteryRes.setBatteryLevel(drone.getBatteryCapacity());
        batteryRes.setStatus(drone.getState());
        return batteryRes;
    }

    @Override
    public List<Drone> availableDrones() {
        return droneRepository.findAllByBatteryCapacityGreaterThanAndState(25, DroneStateEnum.IDLE);
    }

    private BigDecimal sumMedicationWeight(List<Medication> medicationList) {
        return medicationList.isEmpty() ? BigDecimal.ZERO : medicationList.stream().map(Medication::getWeight).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public void checkAllBatteryLevels(){
        List<Drone> drones = droneRepository.findAll();
        for (Drone drone : drones) {
            AuditLog log = new AuditLog();
            log.setDroneId(drone.getId());
            log.setBatteryCapacity(drone.getBatteryCapacity());
            log.setTimestamp(LocalDateTime.now());
            auditLogRepository.save(log);
        }
    }

}
