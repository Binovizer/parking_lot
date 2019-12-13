package com.gojek.parkinglot.service.impl;

import com.gojek.parkinglot.exception.CommandArgumentsException;
import com.gojek.parkinglot.exception.CommandNotSupportedException;
import com.gojek.parkinglot.service.impl.CommandValidator;
import com.gojek.parkinglot.utils.CommandSupported;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * The type ValidatorTest
 *
 * @author Mohd Nadeem
 */
class CommandValidatorTest {

    private CommandValidator validator;

    @BeforeEach
    void init() {
        validator = new CommandValidator();
    }

    @Test
    void validateWithCorrectCommandAndRequiredParameters() {
        String command = "create_parking_lot 6";
        Assertions.assertEquals(CommandSupported.CREATE_PARKING_LOT, validator.validate(command));
    }

    @Test
    void validateWithIncorrectCommand() {
        String command = "create_parking 6";
        Assertions.assertThrows(CommandNotSupportedException.class, () -> validator.validate(command));
    }

    @Test
    void validateWithCorrectCommandWithoutRequiredParameters() {
        String command = "create_parking_lot";
        Assertions.assertThrows(CommandArgumentsException.class, () -> validator.validate(command));
    }
}