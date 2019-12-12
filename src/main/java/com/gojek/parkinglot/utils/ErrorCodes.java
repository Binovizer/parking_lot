package com.gojek.parkinglot.utils;

/**
 * The type ErrorCode
 *
 * @author Mohd Nadeem
 */
public enum ErrorCodes {

    HANDLER_NOT_FOUND("HANDLER_NOT_FOUND", "No handler is supported for this command."),
    SLOT_NOT_AVAILABLE("SLOT_NOT_AVAILABLE", "Slot is already occupied.");

    private final String errorCode;
    private final String message;

    ErrorCodes(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
