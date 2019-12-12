package com.gojek.parkinglot.exception;

/**
 * The type CommandNotSupportedException
 *
 * @author Mohd Nadeem
 */
public class CommandNotSupportedException extends RuntimeException {

    public CommandNotSupportedException(String message) {
        super(message);
    }
}
