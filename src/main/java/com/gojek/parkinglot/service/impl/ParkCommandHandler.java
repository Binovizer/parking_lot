package com.gojek.parkinglot.service.impl;

import com.gojek.parkinglot.dto.Car;
import com.gojek.parkinglot.dto.Slot;
import com.gojek.parkinglot.dto.Vehicle;
import com.gojek.parkinglot.exception.ParkingLotException;
import com.gojek.parkinglot.service.CommandHandler;
import com.gojek.parkinglot.service.ParkingLotService;

/**
 * The type ParkCommand
 *
 * @author Mohd Nadeem
 */
public class ParkCommandHandler implements CommandHandler {

    private ParkingLotService parkingLotService;

    public ParkCommandHandler(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @Override
    public String execute(String[] args) {
        String response;
        String registrationNumber = args[1];
        String color = args[2];
        Vehicle vehicle = new Car(registrationNumber, color);
        try {
            Slot slot = parkingLotService.park(vehicle);
            response = String.format("Allocated slot number: %s", slot.getId());
        }catch (ParkingLotException e){
            response = "Sorry, parking lot is full";
        }
        return response;
    }
}
