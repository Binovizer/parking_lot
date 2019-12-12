package com.gojek.parkinglot.utils;

import java.util.Arrays;
import java.util.Optional;

/**
 * The type CommandSupported
 *
 * @author Mohd Nadeem
 */
public enum CommandSupported {

    UNDEFINED("undefined", 0),
    EXIT("exit", 0),
    CREATE_PARKING_LOT("create_parking_lot", 1),
    PARK("park", 2),
    LEAVE("leave", 1),
    STATUS("status", 0),
    REGISTRATION_NUMBERS_FOR_CARS_WITH_COLOUR("registration_numbers_for_cars_with_colour", 1),
    SLOT_NUMBERS_FOR_CARS_WITH_COLOUR("slot_numbers_for_cars_with_colour", 1),
    SLOT_NUMBER_FOR_REGISTRATION_NUMBER("slot_number_for_registration_number", 1);

    private String name;
    private int noOfRequiredParameters;

    public String getName() {
        return name;
    }

    public int getNoOfRequiredParameters() {
        return noOfRequiredParameters;
    }

    CommandSupported(String name, int noOfRequiredParameters) {
        this.name = name;
        this.noOfRequiredParameters = noOfRequiredParameters;
    }

    public static CommandSupported from(String name) {
        Optional<CommandSupported> foundMaybe = Arrays.stream(CommandSupported.values())
                .filter(commandSupported -> commandSupported.getName().equalsIgnoreCase(name))
                .findFirst();
        return foundMaybe.orElse(UNDEFINED);
    }
}
