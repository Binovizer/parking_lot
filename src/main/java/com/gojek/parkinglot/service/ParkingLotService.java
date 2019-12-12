package com.gojek.parkinglot.service;

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
}
