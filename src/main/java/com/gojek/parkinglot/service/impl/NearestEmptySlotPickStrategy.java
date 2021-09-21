package com.gojek.parkinglot.service.impl;

import com.gojek.parkinglot.dto.Slot;
import com.gojek.parkinglot.dto.VehicleType;
import com.gojek.parkinglot.service.ParkingLotService;
import com.gojek.parkinglot.service.SlotPickStrategy;

/**
 * The type NearestEmptySlotPickStrategy
 *
 * @author nadeem
 */
public class NearestEmptySlotPickStrategy implements SlotPickStrategy {

    private final ParkingLotService parkingLotService;

    public NearestEmptySlotPickStrategy(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }

    @Override
    public Slot pickSlot(VehicleType vehicleType) {
        return parkingLotService.getNearestSlot(vehicleType);
    }

}
