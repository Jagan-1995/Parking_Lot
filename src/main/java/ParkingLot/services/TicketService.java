package ParkingLot.services;

import ParkingLot.exceptions.GateNotFoundException;
import ParkingLot.factory.SpotAssignmentStrategyFactory;
import ParkingLot.models.*;
import ParkingLot.repositories.GateRepository;
import ParkingLot.repositories.ParkingLotRepository;
import ParkingLot.repositories.TicketRepository;
import ParkingLot.repositories.VehicleRepository;
import ParkingLot.strategies.SpotAssignmentStrategy;

import java.util.Date;
import java.util.Optional;

public class TicketService {

    private GateRepository gateRepository;
    private VehicleRepository vehicleRepository;
    private ParkingLotRepository parkingLotRepository;
    private TicketRepository ticketRepository;

    public TicketService(GateRepository gateRepository,
                         VehicleRepository vehicleRepository,
                         ParkingLotRepository parkingLotRepository,
                         TicketRepository ticketRepository){
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.ticketRepository = ticketRepository;
    }
    public Ticket issueTicket(Long gateId,
                              String vehicleNumber,
                              String vehicleOwnerName,
                              VehicleType vehicleType) throws GateNotFoundException {
        Ticket ticket = new Ticket();
        ticket.setEntryTime(new Date()
        );

        // Get the Gate object from the database using the gateId
        Optional<Gate> optionalGate = gateRepository.findGateById(gateId);

        if (optionalGate.isEmpty()){
            throw new GateNotFoundException("Invalid gateId: " + gateId);
        }

        Gate gate = optionalGate.get();
        ticket.setGeneratedAt(gate);
        ticket.setGeneratedBy(gate.getOperator());

        // Get the vehicle with the vehicle number, if present, use it otherwise create a new vehicle object

        Optional<Vehicle> optionalVehicle = vehicleRepository.findVehicleByVehicleNumber(vehicleNumber);

        Vehicle savedVehicle = null;
        if (optionalVehicle.isEmpty()){
            // throw vehicle not found exception
            Vehicle vehicle = new Vehicle();
            vehicle.setVehicleNumber(vehicleNumber);
            vehicle.setVehicleType(vehicleType);
            vehicle.setOwnerName(vehicleOwnerName);
            savedVehicle = vehicleRepository.save(vehicle);
        }
        else {
            savedVehicle = optionalVehicle.get();
        }

        ticket.setVehicle(savedVehicle);

        // Assign the spot

        ParkingLot parkingLot = parkingLotRepository.getParkingLotByGateId(gateId);
        SpotAssignmentStrategyType spotAssignmentStrategyType = parkingLot.getSpotAssignmentStrategyType();

        SpotAssignmentStrategy spotAssignmentStrategy =
                SpotAssignmentStrategyFactory.getSpotAssignmentStrategyForType(spotAssignmentStrategyType);

        ParkingSpot parkingSpot = spotAssignmentStrategy.assignSpot(vehicleType,gate);
        ticket.setParkingSpot(parkingSpot);

        ticket.setNumber("TICKET_"+ gateId +"_"+ ticket.getEntryTime());

        return ticketRepository.save(ticket);

    }

}


/*

1        M
PL ---- Gate => 1 : M
1        1
=> Id of one side on M side.
=> ParkingLot id in the Gate table.
 */