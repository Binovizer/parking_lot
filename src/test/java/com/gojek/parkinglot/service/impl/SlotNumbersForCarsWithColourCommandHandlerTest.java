package com.gojek.parkinglot.service.impl;

import com.gojek.parkinglot.dto.Slot;
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
 * The type SlotNumbersForCarsWithColourCommandHandlerTest
 *
 * @author Mohd Nadeem
 */
@ExtendWith(MockitoExtension.class)
class SlotNumbersForCarsWithColourCommandHandlerTest {

    private static final String[] command = {"slot_numbers_for_cars_with_colour", "White"};
    private static final String DELIMITER = ", ";

    private List<Slot> slots;

    private CommandHandler commandHandler;

    @Mock
    private ParkingLotService parkingLotService;

    @BeforeEach
    void setUp() {
        commandHandler = new SlotNumbersForCarsWithColourCommandHandler(parkingLotService);
        populateSlots();
    }

    @Test
    void executeWithColorFound() {
        when(parkingLotService.searchSlots(any(), anyString())).thenReturn(slots);

        String response = commandHandler.execute(command);
        verify(parkingLotService).searchSlots(any(), anyString());
        String expectedResponse = slots.stream()
                .map(Slot::getId)
                .collect(Collectors.joining(DELIMITER));
        Assertions.assertEquals(expectedResponse, response);
    }

    @Test
    void executeWithColorNotFound() {
        when(parkingLotService.searchSlots(any(), anyString())).thenReturn(Collections.emptyList());

        String response = commandHandler.execute(command);
        verify(parkingLotService).searchSlots(any(), anyString());
        Assertions.assertEquals("Not found.", response);
    }

    private void populateSlots() {
        slots = new ArrayList<>();
        slots.add(new Slot("1"));
        slots.add(new Slot("2"));
        slots.add(new Slot("3"));
        slots.add(new Slot("4"));
    }

}