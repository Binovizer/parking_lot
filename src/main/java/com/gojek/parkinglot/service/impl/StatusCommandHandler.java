package com.gojek.parkinglot.service.impl;

import com.gojek.parkinglot.dto.VehicleType;
import com.gojek.parkinglot.service.CommandHandler;
import com.gojek.parkinglot.service.ParkingLotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type StatusCommand
 *
 * @author Mohd Nadeem
 */
public class StatusCommandHandler implements CommandHandler {

    private static final Logger log = LoggerFactory.getLogger(StatusCommandHandler.class);

    private ParkingLotService parkingLotService;

    public StatusCommandHandler(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @Override
    public String execute(String[] args) {
        log.info("Executing '{}' command.", args[0]);
        parkingLotService.printStatus(VehicleType.CAR);
        return "";
    }
}
