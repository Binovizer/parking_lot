package com.gojek.parkinglot;

import com.gojek.parkinglot.service.Processor;
import com.gojek.parkinglot.service.Validator;
import com.gojek.parkinglot.service.impl.CommandProcessor;
import com.gojek.parkinglot.service.impl.CommandValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * The type ParkingLotApplication
 *
 * @author Mohd Nadeem
 */
public class ParkingLotApplication {

    private static final Logger log = LoggerFactory.getLogger(ParkingLotApplication.class);

    public static void main(String[] args) {
        log.info("Arguments passed to program : {}", Arrays.toString(args));
        Validator validator = new CommandValidator();
        Processor processor = new CommandProcessor(validator);
        processor.process(args);
    }

}
