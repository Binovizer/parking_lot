package com.gojek.parkinglot.service.impl;

import com.gojek.parkinglot.service.CommandHandler;
import com.gojek.parkinglot.service.ParkingLotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type ExitCommand
 *
 * @author Mohd Nadeem
 */
public class ExitCommandHandler implements CommandHandler {

    private static final Logger log = LoggerFactory.getLogger(ExitCommandHandler.class);

    private final ParkingLotService parkingLotService;

    public ExitCommandHandler(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @Override
    public String execute(String[] args) {
        log.info("Executing '{}' command.", args[0]);
        log.info("Exiting the system.");
        System.exit(0);
        return "";
    }
}
