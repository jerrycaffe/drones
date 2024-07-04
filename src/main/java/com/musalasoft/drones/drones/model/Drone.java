package com.musalasoft.drones.drones.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "drones")
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    private String serialNumber;
    @Enumerated
    private DroneModelEnum model;
    private BigDecimal weightLimit;
    private int batteryCapacity;
    @Enumerated
    private DroneStateEnum state;
    @OneToMany(mappedBy = "drone", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Medication> medications;
}
