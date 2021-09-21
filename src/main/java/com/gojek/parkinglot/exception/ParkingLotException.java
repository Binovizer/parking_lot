package com.gojek.parkinglot.exception;

import com.gojek.parkinglot.utils.ErrorCodes;

/**
 * The type ParkingLotNotAvailableException
 *
 * @author Mohd Nadeem
 */
public class ParkingLotException extends RuntimeException {

    private final ErrorCodes errorCodes;

    public ParkingLotException(ErrorCodes errorCodes) {
        this.errorCodes = errorCodes;
    }

    public ErrorCodes getErrorCodes() {
        return errorCodes;
    }
}
