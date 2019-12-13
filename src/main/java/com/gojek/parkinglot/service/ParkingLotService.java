package com.gojek.parkinglot.service;

import com.gojek.parkinglot.dto.Slot;
import com.gojek.parkinglot.dto.Vehicle;
import com.gojek.parkinglot.dto.VehicleType;

import java.util.List;

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
    Vehicle freeSlot(VehicleType type, String slotId);

    /**
     * Prints the status of given vehicle vehicleType
     * @param vehicleType the vehicle vehicleType
     */
    void printStatus(VehicleType vehicleType);

    /**
     * Searches the vehicles of given type and color
     * @param vehicleType the vehicle type to be searched
     * @param color the color of vehicle
     * @return returns the list of vehicles
     */
    List<Vehicle> searchVehicle(VehicleType vehicleType, String color);

    /**
     * Searches the slots containing vehicle of given type and color
     * @param vehicleType the vehicle type
     * @param color the color
     * @return returns the list of slots
     */
    List<Slot> searchSlots(VehicleType vehicleType, String color);

    /**
     * Searches the vehicle of given registration number in given type
     * @param registrationNumber the registration number
     * @param vehicleType the vehicle type
     * @return returns the slot containing the given type of vehicle with given registration number
     */
    Slot search(VehicleType vehicleType, String registrationNumber);
}
