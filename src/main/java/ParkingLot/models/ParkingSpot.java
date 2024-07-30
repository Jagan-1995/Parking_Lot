package ParkingLot.models;

import java.util.List;

public class ParkingSpot extends BaseModel{
    private ParkingSpotStatus parkingSpotStatus;
    private int parkingSpotNumber;
    private List<VehicleType> supportedVehicleTypes;
    private ParkingFloor parkingFloor;

    public ParkingSpotStatus getParkingSpotStatus() {
        return parkingSpotStatus;
    }

    public void setParkingSpotStatus(ParkingSpotStatus parkingSpotStatus) {
        this.parkingSpotStatus = parkingSpotStatus;
    }

    public int getParkingSpotNumber() {
        return parkingSpotNumber;
    }

    public void setParkingSpotNumber(int parkingSpotNumber) {
        this.parkingSpotNumber = parkingSpotNumber;
    }

    public List<VehicleType> getSupportedVehicleTypes() {
        return supportedVehicleTypes;
    }

    public void setSupportedVehicleTypes(List<VehicleType> supportedVehicleTypes) {
        this.supportedVehicleTypes = supportedVehicleTypes;
    }

    public ParkingFloor getParkingFloor() {
        return parkingFloor;
    }

    public void setParkingFloor(ParkingFloor parkingFloor) {
        this.parkingFloor = parkingFloor;
    }
}

/*
PARKING FLOOR : LIST OF PARKING SPOTS
PARKING FLOOR : PARKING SPOT MAPPING ALREADY DONE?
SPOT : FLOOR AGAIN?

FOR EVERY FLOOR, WE HAVE TO GET ALL SPOTS:
    FOR ALL SPOTS, IF WE FIND OUR SPOT - THAT FLOOR IS THE ANSWER

PARKING FLOOR : PARKING SPOT
 1 : M
 1 : 1
 1 : M => ID FLOOR IN SPOT


SPOT - FLOOR?
 */