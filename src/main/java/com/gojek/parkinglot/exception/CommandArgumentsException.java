package com.gojek.parkinglot.exception;

/**
 * The type CommandNotSupportedException
 *
 * @author Mohd Nadeem
 */
public class CommandArgumentsException extends RuntimeException {

    public CommandArgumentsException(String message) {
        super(message);
    }
}
