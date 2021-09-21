package com.gojek.parkinglot.service.impl;

import com.gojek.parkinglot.dto.Car;
import com.gojek.parkinglot.dto.Slot;
import com.gojek.parkinglot.dto.Vehicle;
import com.gojek.parkinglot.exception.ParkingLotException;
import com.gojek.parkinglot.service.CommandHandler;
import com.gojek.parkinglot.service.ParkingLotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type ParkCommand
 *
 * @author Mohd Nadeem
 */
public class ParkCommandHandler implements CommandHandler {

    private static final Logger log = LoggerFactory.getLogger(ParkCommandHandler.class);

    private final ParkingLotService parkingLotService;

    public ParkCommandHandler(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @Override
    public String execute(String[] args) {
        log.info("Executing '{}' command.", args[0]);
        String response = "Sorry, parking lot is full";
        String registrationNumber = args[1];
        String color = args[2];
        Vehicle vehicle = new Car(registrationNumber, color);
        log.info("Parking vehicle : {}", vehicle);
        try {
            Slot slot = parkingLotService.park(vehicle);
            String slotId = slot.getId();
            log.info("Allocated slot number : {}", slotId);
            response = String.format("Allocated slot number: %s", slotId);
        }catch (ParkingLotException e) {
            log.info("Parking is full.");
        }
        return response;
    }
}
