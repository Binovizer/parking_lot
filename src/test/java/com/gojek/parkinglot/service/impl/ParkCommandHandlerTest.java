package com.gojek.parkinglot.service.impl;

import com.gojek.parkinglot.dto.Slot;
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
 * The type ParkCommandHandlerTest
 *
 * @author Mohd Nadeem
 */
@ExtendWith(MockitoExtension.class)
public class ParkCommandHandlerTest {

    private static final String SLOT_ID = "1";

    private String[] command = {"park", "KA-01-HH-1234", "White"};

    private Slot slot;

    private CommandHandler commandHandler;

    @Mock
    private ParkingLotService parkingLotService;

    @BeforeEach
    void setUp() {
        commandHandler = new ParkCommandHandler(parkingLotService);
        populateSlot();
    }

    @Test
    void executeWithCorrectArgumentsParkingAvailable() {
        when(parkingLotService.park(any())).thenReturn(slot);

        String response = commandHandler.execute(command);
        verify(parkingLotService).park(any());
        Assertions.assertEquals(String.format("Allocated slot number: %s", SLOT_ID), response);
    }

    @Test
    void executeWithCorrectArgumentsParkingFull() {
        when(parkingLotService.park(any())).thenThrow(new ParkingLotException(ErrorCodes.PARKING_FULL));

        String response = commandHandler.execute(command);
        verify(parkingLotService).park(any());
        Assertions.assertEquals("Sorry, parking lot is full", response);
    }

    private void populateSlot() {
        slot = new Slot(SLOT_ID);
    }
}