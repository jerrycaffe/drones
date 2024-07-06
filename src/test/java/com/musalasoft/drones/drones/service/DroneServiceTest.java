package com.musalasoft.drones.drones.service;

import com.musalasoft.drones.drones.dto.DroneLoadReq;
import com.musalasoft.drones.drones.dto.DroneRegisterReq;
import com.musalasoft.drones.drones.exception.NotFoundException;
import com.musalasoft.drones.drones.model.Drone;
import com.musalasoft.drones.drones.model.DroneModelEnum;
import com.musalasoft.drones.drones.model.DroneStateEnum;
import com.musalasoft.drones.drones.model.Medication;
import com.musalasoft.drones.drones.repository.DroneRepository;
import com.musalasoft.drones.drones.repository.MedicationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DroneServiceTest {
    @Mock
    DroneRepository droneRepository;
    @Mock
    MedicationRepository medicationRepository;
    @InjectMocks
    DroneServiceImpl droneService;

    private Drone drone;
    private Medication medication;
    private List<Drone> drones;


    @BeforeEach
    public void setUp() {
//        Create drone 1
        drone = new Drone();
        drone.setSerialNumber("FD12009");
        drone.setModel(DroneModelEnum.Lightweight);
        drone.setId(1L);
        drone.setBatteryCapacity(100);
        drone.setWeightLimit(BigDecimal.valueOf(500.20));
        drone.setState(DroneStateEnum.IDLE);
        drone.setMedications(new ArrayList<>());


//        Create drone 2
        Drone lowBatteryDrone = new Drone();
        lowBatteryDrone.setSerialNumber("FD120091");
        lowBatteryDrone.setModel(DroneModelEnum.Lightweight);
        lowBatteryDrone.setId(2L);
        lowBatteryDrone.setBatteryCapacity(20);
        lowBatteryDrone.setWeightLimit(BigDecimal.valueOf(500.20));
        lowBatteryDrone.setState(DroneStateEnum.IDLE);
        lowBatteryDrone.setMedications(new ArrayList<>());

        //        Create drone 3
        Drone loadingDrone = new Drone();
        loadingDrone.setSerialNumber("FD120091");
        loadingDrone.setModel(DroneModelEnum.Lightweight);
        loadingDrone.setId(2L);
        loadingDrone.setBatteryCapacity(20);
        loadingDrone.setWeightLimit(BigDecimal.valueOf(500.20));
        loadingDrone.setState(DroneStateEnum.LOADING);
        loadingDrone.setMedications(new ArrayList<>());
//        Create medication
        medication = new Medication();
        medication.setName("TV");
        medication.setCode("23902");
        medication.setId(1L);
        medication.setWeight(BigDecimal.valueOf(100));

//        List of drones
        drones = List.of(drone, lowBatteryDrone, loadingDrone);
    }

    @Test
    public void shouldRegisterDrone() {
        DroneRegisterReq droneRegisterReq = new DroneRegisterReq();

        droneRegisterReq.setSerialNumber("FD12009");
        droneRegisterReq.setModel(DroneModelEnum.Lightweight);
        droneRegisterReq.setWeightLimit(BigDecimal.valueOf(500.20));


        when(droneRepository.save(any(Drone.class))).thenReturn(drone);

        var response = droneService.register(droneRegisterReq);

        assertNotNull(response);
        assertEquals(droneRegisterReq.getSerialNumber(), response.getSerialNumber());
        assertEquals(100, response.getBatteryCapacity());
        assertEquals(DroneStateEnum.IDLE, response.getState());

    }

    @Test
    public void shouldLoadMedication() {

        DroneLoadReq droneLoadReq = new DroneLoadReq();
        droneLoadReq.setCode("23902");
        droneLoadReq.setName("TV");
        droneLoadReq.setWeight(BigDecimal.valueOf(20.560));

        when(droneRepository.findById(1L)).thenReturn(Optional.of(drone));
        when(medicationRepository.save(any(Medication.class))).thenReturn(medication);

        var response = droneService.load(droneLoadReq, 1L);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals(droneLoadReq.getCode(), response.getCode());
    }

    @Test
    public void shouldThrowNotFoundIfDroneIdIsMissing() {
        DroneLoadReq droneLoadReq = new DroneLoadReq();
        droneLoadReq.setCode("23902");
        droneLoadReq.setName("TV");
        droneLoadReq.setWeight(BigDecimal.valueOf(20.560));

        var exception = assertThrows(NotFoundException.class, () -> droneService.load(droneLoadReq, 1L));

        assertEquals("Drone does not exist", exception.getMessage());

    }

    @Test
    public void shouldReturnBatteryDetails(){
        when(droneRepository.findById(1L)).thenReturn(Optional.of(drone));

        var response = droneService.checkBattery(1L);
        assertNotNull(response);
        assertEquals(100, response.getBatteryLevel());
    }

    @Test
    public void shouldReturnListOfDrones(){
        when(droneRepository.findAllByBatteryCapacityLessThanAndState(25, DroneStateEnum.IDLE)).thenReturn(drones);

        var response = droneService.availableDrones();

        assertEquals(2, response.size());
    }

}