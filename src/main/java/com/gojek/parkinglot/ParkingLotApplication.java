package com.gojek.parkinglot;

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
        Processor.process(args);
    }

}
