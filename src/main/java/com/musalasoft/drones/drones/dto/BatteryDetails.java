package com.musalasoft.drones.drones.dto;

import com.musalasoft.drones.drones.model.DroneStateEnum;
import lombok.Data;


@Data
public class BatteryDetails {
    private DroneStateEnum status;
    private int batteryLevel;
}
