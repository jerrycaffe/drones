package com.musalasoft.drones.drones.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DroneLoadReq {
    @NotBlank(message = "name is required")
    private String name;
    @NotNull(message = "weight is required")
    private BigDecimal weight;
    @NotBlank(message = "code is required")
    private String code;
    private String imageUrl;
}
