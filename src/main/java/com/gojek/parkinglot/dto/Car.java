package com.gojek.parkinglot.dto;

/**
 * The type Car
 *
 * @author Mohd Nadeem
 */
public class Car extends Vehicle {

    public Car(String registrationNumber, String color) {
        super(registrationNumber, color, VehicleType.CAR);
    }
}
