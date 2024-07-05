package com.musalasoft.drones.drones.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "medication")
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal weight;
    private String code;
    private String image;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "drone_id", nullable = false)
    private Drone drone;
}
