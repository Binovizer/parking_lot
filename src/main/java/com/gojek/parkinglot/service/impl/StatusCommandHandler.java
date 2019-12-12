package com.gojek.parkinglot.service.impl;

import com.gojek.parkinglot.dto.VehicleType;
import com.gojek.parkinglot.service.CommandHandler;
import com.gojek.parkinglot.service.ParkingLotService;

/**
 * The type StatusCommand
 *
 * @author Mohd Nadeem
 */
public class StatusCommandHandler implements CommandHandler {

    private ParkingLotService parkingLotService;

    public StatusCommandHandler(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @Override
    public String execute(String[] args) {
        parkingLotService.printStatus(VehicleType.CAR);
        return "";
    }
}
