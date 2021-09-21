package com.gojek.parkinglot.dto;

import com.gojek.parkinglot.exception.ParkingLotException;
import com.gojek.parkinglot.utils.ErrorCodes;

/**
 * The type Slot
 *
 * @author Mohd Nadeem
 */
public class Slot {

    private final String id;
    private Vehicle parkedVehicle;

    public String getId() {
        return id;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public Slot(String id) {
        this.id = id;
    }

    public boolean isEmpty() {
        return parkedVehicle == null;
    }

    public void park(Vehicle car){
        if(isEmpty()){
            this.parkedVehicle = car;
        } else {
            throw new ParkingLotException(ErrorCodes.SLOT_NOT_AVAILABLE);
        }
    }

    public Vehicle empty(){
        Vehicle parkedVehicle = this.parkedVehicle;
        this.parkedVehicle = null;
        return parkedVehicle;
    }
}
