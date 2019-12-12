package com.gojek.parkinglot.service.impl;

import com.gojek.parkinglot.dto.Slot;
import com.gojek.parkinglot.dto.VehicleType;
import com.gojek.parkinglot.exception.ParkingLotException;
import com.gojek.parkinglot.service.CommandHandler;
import com.gojek.parkinglot.service.ParkingLotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type SlotNumberForRegistrationNumber
 *
 * @author Mohd Nadeem
 */
public class SlotNumberForRegistrationNumberCommandHandler implements CommandHandler {

    private static final Logger log = LoggerFactory.getLogger(SlotNumberForRegistrationNumberCommandHandler.class);

    private ParkingLotService parkingLotService;

    public SlotNumberForRegistrationNumberCommandHandler(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @Override
    public String execute(String[] args) {
        log.info("Executing '{}' command.", args[0]);
        String response;
        String registrationNumber = args[1];
        try {
            Slot slot = parkingLotService.search(VehicleType.CAR, registrationNumber);
            response = slot.getId();
        }catch (ParkingLotException e){
            response = "Not found";
        }
        return response;
    }
}
