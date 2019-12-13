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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * The type SlotNumberForRegistrationNumberCommandHandlerTest
 *
 * @author Mohd Nadeem
 */
@ExtendWith(MockitoExtension.class)
public class SlotNumberForRegistrationNumberCommandHandlerTest {

    private static final String[] command = {"slot_number_for_registration_number", "KA-01-HH-3141"};
    private static final String SLOT_ID = "1";

    private CommandHandler commandHandler;

    private Slot slot;

    @Mock
    private ParkingLotService parkingLotService;

    @BeforeEach
    void setUp() {
        commandHandler = new SlotNumberForRegistrationNumberCommandHandler(parkingLotService);
        populateSlot();
    }

    private void populateSlot() {
        slot = new Slot(SLOT_ID);
    }

    @Test
    void executeWithRegistrationNumberFound() {
        when(parkingLotService.searchRegistrationNumber(any(), anyString())).thenReturn(slot);

        String response = commandHandler.execute(command);
        verify(parkingLotService).searchRegistrationNumber(any(), anyString());
        Assertions.assertEquals(SLOT_ID, response);
    }

    @Test
    void executeWithRegistrationNumberNotFound() {
        when(parkingLotService.searchRegistrationNumber(any(), anyString()))
                .thenThrow(new ParkingLotException(ErrorCodes.SLOT_NOT_FOUND));

        String response = commandHandler.execute(command);
        verify(parkingLotService).searchRegistrationNumber(any(), anyString());
        Assertions.assertEquals("Not found", response);
    }
}