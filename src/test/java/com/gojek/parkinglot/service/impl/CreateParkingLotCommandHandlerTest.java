package com.gojek.parkinglot.service.impl;

import com.gojek.parkinglot.service.ParkingLotService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * The type CreateParkingLotCommandHandlerTest
 *
 * @author Mohd Nadeem
 */
@ExtendWith(MockitoExtension.class)
class CreateParkingLotCommandHandlerTest {

    private String[] command = {"create_parking_lot", "6"};
    private String[] invalidCommand = {"create_parking_lot", "Six"};

    private CreateParkingLotCommandHandler createParkingLotCommandHandler;

    @Mock
    private ParkingLotService parkingLotService;

    @BeforeEach
    void setUp() {
        createParkingLotCommandHandler = new CreateParkingLotCommandHandler(parkingLotService);
    }

    @Test
    void executeWithCorrectArguments() {
        String response = createParkingLotCommandHandler.execute(command);
        Assertions.assertEquals(String.format("Created a parking lot with %s slots", command[1]), response);
    }

    @Test
    void executeWithIncorrectArguments() {
        Assertions.assertThrows(NumberFormatException.class, () ->
                createParkingLotCommandHandler.execute(invalidCommand));
    }
}