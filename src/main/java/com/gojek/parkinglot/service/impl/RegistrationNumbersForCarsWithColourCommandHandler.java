package com.gojek.parkinglot.service.impl;

import com.gojek.parkinglot.ParkingLotApplication;
import com.gojek.parkinglot.dto.Vehicle;
import com.gojek.parkinglot.dto.VehicleType;
import com.gojek.parkinglot.service.CommandHandler;
import com.gojek.parkinglot.service.ParkingLotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The type RegistrationNumbersForCarsWithColourCommand
 *
 * @author Mohd Nadeem
 */
public class RegistrationNumbersForCarsWithColourCommandHandler implements CommandHandler {

    private static final Logger log = LoggerFactory.getLogger(RegistrationNumbersForCarsWithColourCommandHandler.class);

    private static final String DELIMITER = ", ";

    private ParkingLotService parkingLotService;

    public RegistrationNumbersForCarsWithColourCommandHandler(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @Override
    public String execute(String[] args) {
        log.info("Executing '{}' command.", args[0]);
        String response = "Not found";
        String color = args[1];
        List<Vehicle> filteredVehicles = parkingLotService.searchVehicle(VehicleType.CAR, color);
        if(Objects.nonNull(filteredVehicles) && filteredVehicles.size() > 0){
            response = filteredVehicles.stream()
                    .map(Vehicle::getRegistrationNumber)
                    .collect(Collectors.joining(DELIMITER));
        }
        return response;
    }
}
