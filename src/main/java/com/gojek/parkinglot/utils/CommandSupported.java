package com.gojek.parkinglot.utils;

import com.gojek.parkinglot.service.CommandHandler;
import com.gojek.parkinglot.service.impl.*;

import java.util.Arrays;
import java.util.Optional;

/**
 * The type CommandSupported
 *
 * @author Mohd Nadeem
 */
public enum CommandSupported {

    UNDEFINED("undefined", 0),
    EXIT("exit", 0, ExitCommandHandler.class),
    CREATE_PARKING_LOT("create_parking_lot", 1, CreateParkingLotCommandHandler.class),
    PARK("park", 2, ParkCommandHandler.class),
    LEAVE("leave", 1, LeaveCommandHandler.class),
    STATUS("status", 0, StatusCommandHandler.class),
    REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR("registration_numbers_for_cars_with_colour",
            1, RegistrationNumbersForCarsWithColourCommandHandler.class),
    SLOT_NUMBERS_FOR_CARS_WITH_COLOUR("slot_numbers_for_cars_with_colour",
            1, SlotNumbersForCarsWithColourCommandHandler.class),
    SLOT_NUMBER_FOR_REGISTRATION_NUMBER("slot_number_for_registration_number",
            1, SlotNumberForRegistrationNumberCommandHandler.class);

    private final String name;
    private final int noOfRequiredParameters;
    private Class<? extends CommandHandler> handlerClass;

    public String getName() {
        return name;
    }

    public int getNoOfRequiredParameters() {
        return noOfRequiredParameters;
    }

    public Class<? extends CommandHandler> getHandlerClass() {
        return handlerClass;
    }

    CommandSupported(String name, int noOfRequiredParameters) {
        this.name = name;
        this.noOfRequiredParameters = noOfRequiredParameters;
    }

    CommandSupported(String name, int noOfRequiredParameters, Class<? extends CommandHandler> handlerClass) {
        this.name = name;
        this.noOfRequiredParameters = noOfRequiredParameters;
        this.handlerClass = handlerClass;
    }

    public static CommandSupported from(String name) {
        Optional<CommandSupported> foundMaybe = Arrays.stream(CommandSupported.values())
                .filter(commandSupported -> commandSupported.getName().equalsIgnoreCase(name))
                .findFirst();
        return foundMaybe.orElse(UNDEFINED);
    }
}
