package com.musalasoft.drones.drones.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DroneLoadReq {
    @NotBlank(message = "name is required")
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$", message = "Name must contain only letters, numbers, '-', and '_'")
    private String name;
    @NotNull(message = "weight is required")
    private BigDecimal weight;
    @NotBlank(message = "code is required")
    private String code;
    private String imageUrl;
}
