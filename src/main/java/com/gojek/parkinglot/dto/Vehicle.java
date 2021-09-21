package com.gojek.parkinglot.dto;

/**
 * The type Vehicle
 *
 * @author Mohd Nadeem
 */
public abstract class Vehicle {

    private final String registrationNumber;
    private final String color;
    private final VehicleType type;

    public Vehicle(String registrationNumber, String color, VehicleType type) {
        this.registrationNumber = registrationNumber;
        this.color = color;
        this.type = type;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getColor() {
        return color;
    }

    public VehicleType getType() {
        return type;
    }
}
