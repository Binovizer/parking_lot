package com.gojek.parkinglot.service;

import com.gojek.parkinglot.dto.Car;
import com.gojek.parkinglot.dto.Slot;
import com.gojek.parkinglot.dto.Vehicle;
import com.gojek.parkinglot.dto.VehicleType;
import com.gojek.parkinglot.exception.ParkingLotException;
import com.gojek.parkinglot.service.impl.ParkingLotServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

/**
 * The type ParkingLotServiceTest
 *
 * @author Mohd Nadeem
 */
@ExtendWith(MockitoExtension.class)
public class ParkingLotServiceTest {

    private static final String REGISTRATION_NUMBER = "KA-01-HH-1234";
    private static final String COLOR = "White";

    private static final Vehicle vehicle = new Car(REGISTRATION_NUMBER, COLOR);;

    private ParkingLotService parkingLotService;

    @BeforeEach
    void setUp() {
        parkingLotService = ParkingLotServiceImpl.getInstance();
        parkingLotService.createParkingSlots(6);
    }

    @Test
    void createParkingSlots() {
    }

    @Test
    void parkSuccess() {
        Slot park = parkingLotService.park(vehicle);

        Assertions.assertEquals("1", park.getId());
    }

    @Test
    void parkFailParkingFull() {
        parkingLotService.createParkingSlots(0);
        Assertions.assertThrows(ParkingLotException.class, () -> parkingLotService.park(vehicle));
    }

    @Test
    void getNearestSlot() {
        Slot nearestSlot = parkingLotService.getNearestSlot(VehicleType.CAR);
        Assertions.assertEquals("1", nearestSlot.getId());
    }

    @Test
    void freeSlotWithInvalidSlotID() {
        Assertions.assertThrows(ParkingLotException.class,
                () -> parkingLotService.freeSlot(VehicleType.CAR, "10"));
    }

    @Test
    void freeSlotWithValidSlotIDWithNoParkedVehicle() {
        Vehicle vehicle = parkingLotService.freeSlot(VehicleType.CAR, "1");
        Assertions.assertNull(vehicle);
    }

    @Test
    void freeSlotWithValidSlotIDWithParkedVehicle() {
        parkingLotService.park(vehicle);
        Vehicle freedVehicle = parkingLotService.freeSlot(VehicleType.CAR, "1");
        Assertions.assertEquals(vehicle.getRegistrationNumber(), freedVehicle.getRegistrationNumber());
    }

    @Test
    void printStatus() {
        parkingLotService.printStatus(VehicleType.CAR);
    }

    @Test
    void searchVehicleWithColorNotFound() {
        List<Vehicle> whiteVehicles = parkingLotService.searchVehicle(VehicleType.CAR, COLOR);

        Assertions.assertEquals(0, whiteVehicles.size());
    }

    @Test
    void searchVehicleWithColorFound() {
        parkingLotService.park(vehicle);
        List<Vehicle> whiteVehicles = parkingLotService.searchVehicle(VehicleType.CAR, COLOR);

        Assertions.assertEquals(1, whiteVehicles.size());
        Assertions.assertEquals(whiteVehicles.get(0).getRegistrationNumber(), vehicle.getRegistrationNumber());
    }

    @Test
    void searchSlotsWithColorFound() {
        parkingLotService.park(vehicle);
        List<Slot> whiteVehicleSlots = parkingLotService.searchSlots(VehicleType.CAR, COLOR);

        Assertions.assertEquals(1, whiteVehicleSlots.size());
        Assertions.assertEquals(whiteVehicleSlots.get(0).getParkedVehicle().getRegistrationNumber(),
                vehicle.getRegistrationNumber());
    }

    @Test
    void searchSlotsWithColorNotFound() {
        List<Slot> whiteVehicleSlots = parkingLotService.searchSlots(VehicleType.CAR, COLOR);

        Assertions.assertEquals(0, whiteVehicleSlots.size());
    }

    @Test
    void searchRegistrationNumberNotFound() {
        Assertions.assertThrows(ParkingLotException.class,
                () -> parkingLotService.searchRegistrationNumber(VehicleType.CAR, REGISTRATION_NUMBER));
    }

    @Test
    void searchRegistrationNumberFound() {
        parkingLotService.park(vehicle);
        Slot found = parkingLotService.searchRegistrationNumber(VehicleType.CAR, REGISTRATION_NUMBER);

        Assertions.assertEquals("1", found.getId());
    }

}