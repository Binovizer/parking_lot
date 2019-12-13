package com.gojek.parkinglot.service.impl;

import com.gojek.parkinglot.dto.VehicleType;
import com.gojek.parkinglot.exception.ParkingLotException;
import com.gojek.parkinglot.service.CommandHandler;
import com.gojek.parkinglot.service.ParkingLotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type LeaveCommand
 *
 * @author Mohd Nadeem
 */
public class LeaveCommandHandler implements CommandHandler {

    private static final Logger log = LoggerFactory.getLogger(LeaveCommandHandler.class);

    private ParkingLotService parkingLotService;

    public LeaveCommandHandler(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @Override
    public String execute(String[] args) {
        log.info("Executing '{}' command.", args[0]);
        String respnose = "Not found.";
        String slotId = args[1];
        log.info("Emptying the slot with id : {}", slotId);
        try {
            parkingLotService.freeSlot(VehicleType.CAR, slotId);
            respnose = String.format("Slot number %s is free", slotId);
        } catch (ParkingLotException e){
            log.info("Slot was not found with id : {}", slotId);
        }
        return respnose;
    }
}
