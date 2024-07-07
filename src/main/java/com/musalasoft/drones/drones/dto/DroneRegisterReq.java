package com.musalasoft.drones.drones.dto;

import com.musalasoft.drones.drones.model.DroneModelEnum;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DroneRegisterReq {
    @NotBlank(message = "Serial number cannot be blank")
    @Size(message = "Serial number must be between 1 and 100", max = 100, min = 1)
    private String serialNumber;
    @NotNull(message = "drone model is required")
    private DroneModelEnum model;
    @NotNull(message = "Drone weight is required")
    private BigDecimal weightLimit;

}
