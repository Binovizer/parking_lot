package com.gojek.parkinglot.service.impl;

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
    private static ParkingLotServiceImpl parkingLotService;

    private ParkingLotServiceImpl() {
    }

    public static ParkingLotServiceImpl getInstance() {
        if (parkingLotService == null) {
            parkingLotService = new ParkingLotServiceImpl();
        }
        return parkingLotService;
    }


    private Map<VehicleType, List<Slot>> slots;

    public Map<VehicleType, List<Slot>> getSlots() {
        return slots;
    }

    @Override
    public void createParkingSlots(int noOfCarSlots) {
        log.info("Creating parking slots for vehicles.");
        slots = new HashMap<>();
        VehicleType vehicleType = VehicleType.CAR; // Currently, only one vehicle type is supported
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
    public Vehicle freeSlot(VehicleType type, String slotId) {
        log.info("Emptying the given slot with id");
        List<Slot> slots = this.slots.get(type);
        Optional<Slot> optionalSlot =
                slots.stream().filter(slot -> slot.getId().equalsIgnoreCase(slotId)).findFirst();
        Slot slot = optionalSlot.orElseThrow(() -> new ParkingLotException(ErrorCodes.SLOT_NOT_FOUND));
        return slot.empty();
    }

    @Override
    public void printStatus(VehicleType vehicleType) {
        System.out.println("Slot No.\tRegistration No\tColour");
        printStatusOfType(vehicleType);
    }

    @Override
    public List<Vehicle> searchVehicle(VehicleType vehicleType, String color) {
        List<Slot> filteredSlots = searchSlots(vehicleType, color);
        return filteredSlots.stream()
                .map(Slot::getParkedVehicle)
                .collect(Collectors.toList());
    }

    @Override
    public List<Slot> searchSlots(VehicleType vehicleType, String color) {
        List<Slot> slots = this.slots.get(vehicleType);
        return slots.stream()
                .filter(slot -> isVehicleOfColorParkedInSlot(color, slot)).collect(Collectors.toList());
    }

    @Override
    public Slot searchRegistrationNumber(VehicleType vehicleType, String registrationNumber) {
        Optional<Slot> first = slots.get(vehicleType).stream()
                .filter(slot -> isVehicleOfRegistrationNumberParkedInSlot(registrationNumber, slot))
                .findFirst();
        return first.orElseThrow(() -> new ParkingLotException(ErrorCodes.VEHICLE_NOT_FOUND));
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

    private boolean isVehicleOfRegistrationNumberParkedInSlot(String registrationNumber, Slot slot) {
        boolean parked;
        if(slot.isEmpty()){
            parked = false;
        } else {
            parked = slot.getParkedVehicle().getRegistrationNumber().equalsIgnoreCase(registrationNumber);
        }
        return parked;
    }

    private boolean isVehicleOfColorParkedInSlot(String color, Slot slot) {
        boolean parked;
        if(slot.isEmpty()){
            parked = false;
        } else {
            parked = slot.getParkedVehicle().getColor().equalsIgnoreCase(color);
        }
        return parked;
    }
}
