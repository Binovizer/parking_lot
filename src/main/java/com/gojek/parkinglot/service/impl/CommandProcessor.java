package com.gojek.parkinglot.service.impl;

import com.gojek.parkinglot.exception.CommandArgumentsException;
import com.gojek.parkinglot.exception.CommandNotSupportedException;
import com.gojek.parkinglot.exception.ParkingLotException;
import com.gojek.parkinglot.service.CommandHandler;
import com.gojek.parkinglot.service.ParkingLotService;
import com.gojek.parkinglot.service.Processor;
import com.gojek.parkinglot.service.Validator;
import com.gojek.parkinglot.utils.CommandSupported;
import com.gojek.parkinglot.utils.ErrorCodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

/**
 * The type Processor
 *
 * @author Mohd Nadeem
 */
public class CommandProcessor implements Processor {

    private static final Logger log = LoggerFactory.getLogger(CommandProcessor.class);

    private static final String SPACE = " ";

    private final Validator validator;

    public CommandProcessor(Validator validator) {
        this.validator = validator;
    }

    public void process(String[] args) {
        log.info("Processing the input : {}", Arrays.toString(args));
        InputStream inputStream = getInputStream(args);
        Scanner in = new Scanner(inputStream);
        while(in.hasNextLine()) {
            String commandWithArgumentsString = in.nextLine();
            log.info(String.format("Processing Command with arguments : %s", commandWithArgumentsString));
            CommandSupported commandSupported;
            try {
                commandSupported = validator.validate(commandWithArgumentsString);
                log.info("Command is valid.");
                CommandHandler commandHandler = getCommandHandler(commandSupported);
                String[] commandWithArguments = commandWithArgumentsString.split(SPACE);

                String response = commandHandler.execute(commandWithArguments);

                log.info("Command : {}, Response : {}", commandWithArgumentsString, response);
                if(!response.trim().equalsIgnoreCase("")){
                    System.out.println(response);
                }
            } catch (CommandNotSupportedException | CommandArgumentsException | ParkingLotException e){
                log.warn(e.getMessage());
                log.warn("Skipping this command.");
            }
        }
        in.close();
    }

    /**
     * Gets the command handler for given command
     * @param commandSupported the command
     * @return returns the handler
     */
    private CommandHandler getCommandHandler(CommandSupported commandSupported) {
        log.info("Getting command handler for the command : {}", commandSupported.getName());
        CommandHandler commandHandler;
        Class<? extends CommandHandler> handler = commandSupported.getHandlerClass();
        try {
            ParkingLotService parkingLotService = ParkingLotServiceImpl.getInstance();
            commandHandler = handler.getConstructor(ParkingLotService.class).newInstance(parkingLotService);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            log.warn("Unable to instantiate the handler for command : {}", commandSupported.getName());
            throw new ParkingLotException(ErrorCodes.HANDLER_NOT_FOUND);
        }
        return commandHandler;
    }

    /**
     * Gets the input stream based on the mode of input
     * @param args the arguments needed to decide the mode of input
     * @return returns the input stream based on the mode of input
     */
    private InputStream getInputStream(String[] args) {
        log.info("Getting input stream based on the the given arguments : {}", Arrays.toString(args));
        String fileName = null;
        if(Objects.nonNull(args) && args.length > 0){
            fileName = args[0];
            log.info(String.format("File argument with value : %s", fileName));
        }
        InputStream inputStream = System.in;
        if(Objects.nonNull(fileName)){
            try {
                inputStream = new FileInputStream(fileName);
            } catch (FileNotFoundException e) {
                log.error("File not found with the name : {}.", fileName, e);
                log.error("Exiting the system.");
                System.exit(0);
            }
        }
        return inputStream;
    }

}
