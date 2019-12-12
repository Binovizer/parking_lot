package com.gojek.parkinglot.service.impl;

import com.gojek.parkinglot.service.CommandHandler;
import com.gojek.parkinglot.service.ParkingLotService;

/**
 * The type CreateParkingLotCommand
 *
 * @author Mohd Nadeem
 */
public class CreateParkingLotCommandHandler implements CommandHandler {

    private ParkingLotService parkingLotService;

    public CreateParkingLotCommandHandler(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @Override
    public String execute(String[] args) {
        int noOfCarSlots = Integer.parseInt(args[1]);
        parkingLotService.createParkingSlots(noOfCarSlots);
        return String.format("Created a parking lot with %s slots", noOfCarSlots);
    }

}
