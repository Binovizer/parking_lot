package com.gojek.parkinglot.dto;

/**
 * The type VehicleType
 *
 * @author Mohd Nadeem
 */
public enum VehicleType {

    CAR("car"), BIKE("bike");

    private String name;

    VehicleType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
