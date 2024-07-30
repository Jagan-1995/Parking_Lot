package ParkingLot;

import ParkingLot.controllers.TicketController;
import ParkingLot.dtos.IssueTicketRequestDTO;
import ParkingLot.dtos.IssueTicketResponseDTO;
import ParkingLot.models.Ticket;
import ParkingLot.models.VehicleType;
import ParkingLot.repositories.GateRepository;
import ParkingLot.repositories.ParkingLotRepository;
import ParkingLot.repositories.TicketRepository;
import ParkingLot.repositories.VehicleRepository;
import ParkingLot.services.TicketService;

public class Client {
    public static void main(String[] args) {
        // Create a Ticket
        IssueTicketRequestDTO requestDTO = new IssueTicketRequestDTO();
        requestDTO.setGateId(84L);
        requestDTO.setVehicleNumber("TN 39 CD 0001");
        requestDTO.setVehicleType(VehicleType.TWO_WHEELER);
        requestDTO.setVehicleOwnerName("Jagan");

        TicketRepository ticketRepository = new TicketRepository();
        ParkingLotRepository parkingLotRepository = new ParkingLotRepository();
        GateRepository gateRepository = new GateRepository();
        VehicleRepository vehicleRepository = new VehicleRepository();

        TicketService ticketService = new TicketService(
                gateRepository,
                vehicleRepository,
                parkingLotRepository,
                ticketRepository
        );

        TicketController ticketController = new TicketController(ticketService);

        IssueTicketResponseDTO responseDTO = ticketController.issueTicket(requestDTO);
        Ticket ticket = responseDTO.getTicket();
    }
}

/*
For ticket controller, you need ticket service
For ticket service, you need repository objects

dependency graph
 */