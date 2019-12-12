package com.gojek.parkinglot.service.impl;

import com.gojek.parkinglot.service.CommandHandler;

/**
 * The type CreateParkingLotCommand
 *
 * @author Mohd Nadeem
 */
public class CreateParkingLotCommandHandler implements CommandHandler {

    @Override
    public String execute(String[] args) {
        int noOfCarSlots = Integer.parseInt(args[1]);
        return String.format("Created a parking lot with %s slots", noOfCarSlots);
    }

}
