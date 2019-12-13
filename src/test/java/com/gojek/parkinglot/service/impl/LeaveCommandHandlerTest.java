package com.gojek.parkinglot.service.impl;

import com.gojek.parkinglot.dto.Car;
import com.gojek.parkinglot.dto.Vehicle;
import com.gojek.parkinglot.exception.ParkingLotException;
import com.gojek.parkinglot.service.CommandHandler;
import com.gojek.parkinglot.service.ParkingLotService;
import com.gojek.parkinglot.utils.ErrorCodes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * The type LeaveCommandHandlerTest
 *
 * @author Mohd Nadeem
 */
@ExtendWith(MockitoExtension.class)
public class LeaveCommandHandlerTest {

    private static final String REGISTRATION_NUMBER = "KA-01-HH-1234";
    private static final String COLOR = "White";

    private static final String[] command = {"leave", "4"};

    private CommandHandler commandHandler;

    private Vehicle vehicle;

    @Mock
    private ParkingLotService parkingLotService;

    @BeforeEach
    void setUp() {
        commandHandler = new LeaveCommandHandler(parkingLotService);
        populateVehicle();
    }

    private void populateVehicle() {
        vehicle = new Car(REGISTRATION_NUMBER, COLOR);
    }

    @Test
    void executeWithCorrectSlotId() {
        when(parkingLotService.freeSlot(any(), any())).thenReturn(vehicle);

        String response = commandHandler.execute(command);
        verify(parkingLotService).freeSlot(any(), any());
        Assertions.assertEquals(String.format("Slot number %s is free", command[1]), response);
    }

    @Test
    void executeWithIncorrectSlotId() {
        when(parkingLotService.freeSlot(any(), any())).thenThrow(new ParkingLotException(ErrorCodes.SLOT_NOT_FOUND));

        String response = commandHandler.execute(command);
        verify(parkingLotService).freeSlot(any(), any());
        Assertions.assertEquals("Not found.", response);
    }
}