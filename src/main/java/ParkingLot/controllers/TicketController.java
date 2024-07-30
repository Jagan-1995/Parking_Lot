package ParkingLot.controllers;

import ParkingLot.dtos.IssueTicketRequestDTO;
import ParkingLot.dtos.IssueTicketResponseDTO;
import ParkingLot.dtos.ResponseStatus;
import ParkingLot.models.Ticket;
import ParkingLot.services.TicketService;

public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }

    public IssueTicketResponseDTO issueTicket(IssueTicketRequestDTO requestDTO){

        IssueTicketResponseDTO responseDTO = new IssueTicketResponseDTO();

        try {
            Ticket ticket = ticketService.issueTicket(
                    requestDTO.getGateId(),
                    requestDTO.getVehicleNumber(),
                    requestDTO.getVehicleOwnerName(),
                    requestDTO.getVehicleType()
            );
            responseDTO.setTicket(ticket);
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        }
        catch (Exception ex){
            ex.getMessage();
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }

        return responseDTO;

    }
}
