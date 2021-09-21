package com.gojek.parkinglot.service;

import com.gojek.parkinglot.dto.Slot;
import com.gojek.parkinglot.dto.VehicleType;

/**
 * The type SlotPickStrategy
 *
 * @author nadeem
 * Date : 21/09/21
 */
public interface SlotPickStrategy {

    /**
     * Picks the slot
     *
     * @return the slot
     */
    Slot pickSlot(VehicleType vehicleType);
}
