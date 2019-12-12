package com.gojek.parkinglot.service.impl;

import com.gojek.parkinglot.ParkingLotApplication;
import com.gojek.parkinglot.dto.Slot;
import com.gojek.parkinglot.dto.Vehicle;
import com.gojek.parkinglot.dto.VehicleType;
import com.gojek.parkinglot.exception.ParkingLotException;
import com.gojek.parkinglot.service.ParkingLotService;
import com.gojek.parkinglot.utils.ErrorCodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * The type ParkingLotServiceImpl
 *
 * @author Mohd Nadeem
 */
public class ParkingLotServiceImpl implements ParkingLotService {

    private static final Logger log = LoggerFactory.getLogger(ParkingLotApplication.class);

    // Singleton Pattern
    private static ParkingLotServiceImpl parkingLot = null;

    private ParkingLotServiceImpl() {
    }

    public static ParkingLotServiceImpl getInstance() {
        if (parkingLot == null) {
            parkingLot = new ParkingLotServiceImpl();
        }
        return parkingLot;
    }


    private Map<VehicleType, List<Slot>> slots;

    @Override
    public void createParkingSlots(int noOfCarSlots) {
        log.info("Creating parking slots for vehicles.");
        slots = new HashMap<>();
        VehicleType vehicleType = VehicleType.CAR; // Currently only one vehicle type is supported
        List<Slot> typeOfSlots = new ArrayList<>();
        for (int i = 1; i <= noOfCarSlots; i++) {
            Slot slot = new Slot(String.valueOf(i));
            typeOfSlots.add(slot);
        }
        slots.putIfAbsent(vehicleType, typeOfSlots);
    }

    @Override
    public Slot park(Vehicle vehicle) {
        Slot nearestSlot = getNearestSlot(vehicle.getType());
        nearestSlot.park(vehicle);
        return nearestSlot;
    }

    @Override
    public Slot getNearestSlot(VehicleType vehicleType) {
        Optional<Slot> nearestEmptySlot = slots.get(vehicleType).stream().filter(Slot::isEmpty).findFirst();
        return nearestEmptySlot.orElseThrow(
                () -> new ParkingLotException(ErrorCodes.PARKING_FULL));
    }
}
