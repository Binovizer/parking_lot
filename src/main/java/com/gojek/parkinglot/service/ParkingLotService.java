package com.gojek.parkinglot.service;

import com.gojek.parkinglot.dto.Slot;
import com.gojek.parkinglot.dto.Vehicle;
import com.gojek.parkinglot.dto.VehicleType;

/**
 * The type ParkingLotService
 *
 * @author Mohd Nadeem
 */
public interface ParkingLotService {

    /**
     * Creates the parking lot with given no of car slots
     * @param noOfCarSlots the no of car slots
     */
    void createParkingSlots(int noOfCarSlots);

    /**
     * Parks the vehicle
     * @param vehicle the vehicle to be parked
     * @return returns the slot of parked vehicle
     */
    Slot park(Vehicle vehicle);

    /**
     * Gets the nearest available slot for the vehicle
     * @param vehicleType the type of vehicle
     * @return returns the first available slot
     */
    Slot getNearestSlot(VehicleType vehicleType);

    /**
     * Frees the given slot id
     * @param type the type of vehicle parked
     * @param slotId the slot id
     * @return returns the freed vehicle
     */
    Vehicle free(VehicleType type, String slotId);

    /**
     * Prints the status of given vehicle vehicleType
     * @param vehicleType the vehicle vehicleType
     */
    void printStatus(VehicleType vehicleType);
}
