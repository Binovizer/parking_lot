package com.gojek.parkinglot.service.impl;

import com.gojek.parkinglot.dto.Car;
import com.gojek.parkinglot.dto.Vehicle;
import com.gojek.parkinglot.service.CommandHandler;
import com.gojek.parkinglot.service.ParkingLotService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * The type RegistrationNumbersForCarsWithColourCommandHandlerTest
 *
 * @author Mohd Nadeem
 */
@ExtendWith(MockitoExtension.class)
class RegistrationNumbersForCarsWithColourCommandHandlerTest {

    private static final String[] command = {"registration_numbers_for_cars_with_colour", "White"};

    private static final String DELIMITER = ", ";

    private List<Vehicle> vehicles;

    private CommandHandler commandHandler;

    @Mock
    private ParkingLotService parkingLotService;

    @BeforeEach
    void setUp() {
        commandHandler = new RegistrationNumbersForCarsWithColourCommandHandler(parkingLotService);
        populateVehiclesList();
    }

    @Test
    void executeWithColorPresent() {
        when(parkingLotService.searchVehicle(any(), anyString())).thenReturn(vehicles);

        String response = commandHandler.execute(command);
        verify(parkingLotService).searchVehicle(any(), anyString());
        String expectedResponse = vehicles.stream()
                .map(Vehicle::getRegistrationNumber)
                .collect(Collectors.joining(DELIMITER));
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    void executeWithColorNotFound() {
        when(parkingLotService.searchVehicle(any(), anyString())).thenReturn(Collections.emptyList());

        String response = commandHandler.execute(command);
        verify(parkingLotService).searchVehicle(any(), anyString());
        Assertions.assertEquals("Not found", response);
    }

    private void populateVehiclesList() {
        vehicles = new ArrayList<>();
        vehicles.add(new Car("KA-01-HH-1234", "White"));
        vehicles.add(new Car("KA-01-HH-9999", "White"));
        vehicles.add(new Car("KA-01-BB-0001", "White"));
        vehicles.add(new Car("KA-01-HH-7777", "White"));
    }
}