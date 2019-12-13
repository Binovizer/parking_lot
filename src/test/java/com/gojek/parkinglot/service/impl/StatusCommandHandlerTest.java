package com.gojek.parkinglot.service.impl;

import com.gojek.parkinglot.service.CommandHandler;
import com.gojek.parkinglot.service.ParkingLotService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

/**
 * The type StatusCommandHandlerTest
 *
 * @author Mohd Nadeem
 */
@ExtendWith(MockitoExtension.class)
public class StatusCommandHandlerTest {

    private static final String[] command = {"status"};

    private CommandHandler commandHandler;

    @Mock
    private ParkingLotService parkingLotService;

    @BeforeEach
    void setUp() {
        commandHandler = new StatusCommandHandler(parkingLotService);
    }

    @Test
    void execute() {
        doNothing().when(parkingLotService).printStatus(any());

        commandHandler.execute(command);
        verify(parkingLotService).printStatus(any());
    }
}