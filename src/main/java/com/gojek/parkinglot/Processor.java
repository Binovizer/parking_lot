package com.gojek.parkinglot;

import com.gojek.parkinglot.exception.CommandArgumentsException;
import com.gojek.parkinglot.exception.CommandNotSupportedException;
import com.gojek.parkinglot.utils.CommandSupported;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

/**
 * The type Processor
 *
 * @author Mohd Nadeem
 */
public class Processor {

    private static final Logger log = LoggerFactory.getLogger(Processor.class);

    public static void process(String[] args) {
        log.info("Processing the input : {}", Arrays.toString(args));
        InputStream inputStream = getInputStream(args);
        Scanner in = new Scanner(inputStream);
        while(in.hasNextLine()) {
            String commandWithArguments = in.nextLine();
            log.info(String.format("Processing Command with arguments : %s", commandWithArguments));
            CommandSupported commandSupported;
            try {
                commandSupported = Validator.validate(commandWithArguments);
            } catch (CommandNotSupportedException | CommandArgumentsException e){
                log.warn(e.getMessage());
                log.warn("Skipping this command.");
                continue;
            }
            log.info("Command is valid.");
        }
        in.close();
    }

    /**
     * Gets the input stream based on the mode of input
     * @param args the arguments needed to decide the mode of input
     * @return returns the input stream based on the mode of input
     */
    private static InputStream getInputStream(String[] args) {
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
