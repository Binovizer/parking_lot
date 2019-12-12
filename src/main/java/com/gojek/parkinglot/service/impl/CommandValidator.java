package com.gojek.parkinglot.service.impl;

import com.gojek.parkinglot.exception.CommandArgumentsException;
import com.gojek.parkinglot.exception.CommandNotSupportedException;
import com.gojek.parkinglot.service.Validator;
import com.gojek.parkinglot.utils.CommandSupported;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The type Validator
 *
 * @author Mohd Nadeem
 */
public class CommandValidator implements Validator {

    private static final Logger log = LoggerFactory.getLogger(CommandProcessor.class);

    private static final String SPACE = " ";

    @Override
    public CommandSupported validate(String commandWithArguments) {
        log.info("Validating if given command '{}' is valid and have required parameters.", commandWithArguments);
        String[] commandArray = commandWithArguments.split(SPACE);
        String commandName = commandArray[0];
        CommandSupported commandSupported = CommandSupported.from(commandName);
        if(commandSupported == CommandSupported.UNDEFINED){
            String formattedErrorMessage = String.format("Command '%s' not supported.", commandName);
            throw new CommandNotSupportedException(formattedErrorMessage);
        }
        int noOfRequiredParameters = commandSupported.getNoOfRequiredParameters();
        if(commandArray.length-1 < noOfRequiredParameters){
            String formattedErrorMessage = String.format("Command '%s' requires %s parameters but found %s.",
                    commandName, noOfRequiredParameters, commandArray.length - 1);
            throw new CommandArgumentsException(formattedErrorMessage);
        }
        return commandSupported;
    }
}
