package com.gojek.parkinglot.service.impl;

import com.gojek.parkinglot.service.CommandHandler;
import com.gojek.parkinglot.service.ParkingLotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type CreateParkingLotCommand
 *
 * @author Mohd Nadeem
 */
public class CreateParkingLotCommandHandler implements CommandHandler {

    private static final Logger log = LoggerFactory.getLogger(CreateParkingLotCommandHandler.class);

    private final ParkingLotService parkingLotService;

    public CreateParkingLotCommandHandler(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @Override
    public String execute(String[] args) {
        log.info("Executing '{}' command.", args[0]);
        int noOfCarSlots = Integer.parseInt(args[1]);
        log.info("Creating parking lot with {} car slots.", noOfCarSlots);
        parkingLotService.createParkingSlots(noOfCarSlots);
        return String.format("Created a parking lot with %s slots", noOfCarSlots);
    }

}
