package org.citadel.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.citadel.entities.DTOs.UpdateTicketDTO;
import org.citadel.entities.DTOs.CreateTicketDTO;
import org.citadel.services.TicketService;

import java.util.List;

@Path("/tickets")
@Produces(MediaType.APPLICATION_JSON)
public class TicketResource {

    private final TicketService ticketService;

    @Inject
    public TicketResource(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @POST
    @Path("/seed")
    public Response seed(List<CreateTicketDTO> createTicketDTOs) {
        return ticketService.seed(createTicketDTOs);
    }

    @POST
    public Response createTicket(CreateTicketDTO createTicketDto) {
        return ticketService.createTicket(createTicketDto);
    }

    @PATCH
    @Path("/activate/{code}")
    public Response activateTicket(@PathParam("code") String code) {
        return ticketService.activateTicket(code);
    }

    @GET
    @Path("/all")
    public Response findTickets(@QueryParam("page") @DefaultValue("1") int currentPage,
                                @QueryParam("number-of-page") @DefaultValue("5") int itemsPerPage) {
        return ticketService.findTickets(currentPage, itemsPerPage);
    }

    @GET
    @Path("/by-date-ascending")
    public Response findAllTicketByDateDescending() {
        return this.ticketService.findAllTicketByDateDescending();
    }

    @GET
    @Path("/{code}")
    public Response getTicketByCode(@PathParam("code") String code) {
        return ticketService.findTicketByCode(code);
    }

    @GET
    @Path("/status-ticket/{code}")
    public Response getTicketStatusByCode(@PathParam("code") String code) {
        return ticketService.findTicketStatusByCode(code);
    }

    @GET
    @Path("/by-email/{email}")
    public Response getTicketByEmail(@PathParam("email") String email) {
        return ticketService.findTicketByEmail(email);
    }

    @DELETE
    @Path("/{code}")
    public Response deleteTicketByCode(@PathParam("code") String code) {
        return ticketService.deleteTicketByCode(code);
    }

    @PUT
    @Path("/update-ticket/{id}")
    public Response updateTicket(@PathParam("id") Long id, UpdateTicketDTO updateTicketDto) {
        return ticketService.updateTicket(id, updateTicketDto);
    }

    @DELETE
    @Path("/all-delete")
    public Response deleteTickets() {
        return ticketService.deleteTickets();
    }
}
