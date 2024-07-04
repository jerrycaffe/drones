package com.musalasoft.drones.drones.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@Table(name = "medication")
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal weight;
    private String code;
    private String image;

    @ManyToOne
    @JoinColumn(name = "drone_id", nullable = false)
    private Drone drone;
}
