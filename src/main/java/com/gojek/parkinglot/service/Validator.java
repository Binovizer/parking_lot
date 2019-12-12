package com.gojek.parkinglot.service;

import com.gojek.parkinglot.utils.CommandSupported;

/**
 * The type Validator
 *
 * @author Mohd Nadeem
 */
public interface Validator {

    /**
     * Validates the command with arguments
     * @param commandWithArguments the command with arguments
     * @return returns the CommandSupported is command is supported and valid
     */
    CommandSupported validate(String commandWithArguments);

}
