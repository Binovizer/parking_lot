package com.gojek.parkinglot.dto;

/**
 * The type Bike
 *
 * @author Mohd Nadeem
 */
public class Bike extends Vehicle {

    public Bike(String registrationNumber, String color) {
        super(registrationNumber, color, VehicleType.BIKE);
    }

}
