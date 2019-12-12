package com.gojek.parkinglot.service.impl;

import com.gojek.parkinglot.dto.Slot;
import com.gojek.parkinglot.dto.VehicleType;
import com.gojek.parkinglot.service.CommandHandler;
import com.gojek.parkinglot.service.ParkingLotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The type SlotNumbersForCarsWithColourCommand
 *
 * @author Mohd Nadeem
 */
public class SlotNumbersForCarsWithColourCommandHandler implements CommandHandler {

    private static final Logger log = LoggerFactory.getLogger(SlotNumbersForCarsWithColourCommandHandler.class);

    private static final String DELIMITER = ", ";

    private ParkingLotService parkingLotService;

    public SlotNumbersForCarsWithColourCommandHandler(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @Override
    public String execute(String[] args) {
        log.info("Executing '{}' command.", args[0]);
        String response = "Not found.";
        String color = args[1];
        List<Slot> filteredSlots = parkingLotService.searchSlots(VehicleType.CAR, color);
        if(Objects.nonNull(filteredSlots) && filteredSlots.size() > 0) {
            response = filteredSlots.stream()
                    .map(Slot::getId)
                    .collect(Collectors.joining(DELIMITER));
        }
        return response;
    }
}
