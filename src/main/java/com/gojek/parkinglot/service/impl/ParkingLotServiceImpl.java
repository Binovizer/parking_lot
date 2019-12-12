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
import java.util.stream.Collectors;

/**
 * The type ParkingLotServiceImpl
 *
 * @author Mohd Nadeem
 */
public class ParkingLotServiceImpl implements ParkingLotService {

    private static final Logger log = LoggerFactory.getLogger(ParkingLotServiceImpl.class);

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
        log.info("Parking vehicle to nearest slot.");
        Slot nearestSlot = getNearestSlot(vehicle.getType());
        nearestSlot.park(vehicle);
        return nearestSlot;
    }

    @Override
    public Slot getNearestSlot(VehicleType vehicleType) {
        log.info("Getting nearest slot for the vehicle.");
        Optional<Slot> nearestEmptySlot = slots.get(vehicleType).stream().filter(Slot::isEmpty).findFirst();
        return nearestEmptySlot.orElseThrow(
                () -> new ParkingLotException(ErrorCodes.PARKING_FULL));
    }

    @Override
    public Vehicle free(VehicleType type, String slotId) {
        log.info("Emptying the given slot with id");
        List<Slot> slots = this.slots.get(type);
        Optional<Slot> optionalSlot =
                slots.stream().filter(slot -> slot.getId().equalsIgnoreCase(slotId)).findFirst();
        Slot slot = optionalSlot.orElseThrow(() -> new ParkingLotException(ErrorCodes.SLOT_NOT_FOUND));
        Vehicle free = slot.empty();
        return free;
    }

    @Override
    public void printStatus(VehicleType vehicleType) {
        System.out.println("Slot No.\tRegistration No\tColour");
        printStatusOfType(vehicleType);
    }

    @Override
    public List<Vehicle> searchVehicle(VehicleType vehicleType, String color) {
        List<Slot> filteredSlots = searchSlots(vehicleType, color);
        List<Vehicle> filteredVehicles = filteredSlots.stream()
                .map(Slot::getParkedVehicle)
                .collect(Collectors.toList());
        return filteredVehicles;
    }

    public List<Slot> searchSlots(VehicleType vehicleType, String color) {
        List<Slot> slots = this.slots.get(vehicleType);
        List<Slot> filteredSlots = slots.stream()
                .filter(slot -> slot.getParkedVehicle().getColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
        return filteredSlots;
    }


    private void printStatusOfType(VehicleType type) {
        List<Slot> slotsOfType = this.slots.get(type);
        slotsOfType.forEach(slot -> {
            Vehicle parkedVehicle = slot.getParkedVehicle();
            if(Objects.nonNull(parkedVehicle)) {
                String slotPrint = String.format("%s\t%s\t%s",
                        slot.getId(), parkedVehicle.getRegistrationNumber(), parkedVehicle.getColor());
                System.out.println(slotPrint);
            }
        });
    }
}
