package org.citadel.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.citadel.entities.DTOs.CreateTicketDTO;
import org.citadel.entities.DTOs.UpdateTicketDTO;
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
    public Response loadTickets(List<CreateTicketDTO> createTicketDTOs) {
        return ticketService.loadTickets(createTicketDTOs);
    }

    @POST
    public Response createTicket(CreateTicketDTO createTicketDto) {
        return ticketService.createTicket(createTicketDto);
    }

    @PATCH
    @Path("/activate-ticket/{code}")
    public Response activateTicket(@PathParam("code") String code) {
        return ticketService.activateTicket(code);
    }

    @GET
    @Path("/all")
    public Response findTickets(@QueryParam("current-page") @DefaultValue("1") int currentPage,
                                @QueryParam("items-per-page") @DefaultValue("20") int itemsPerPage) {
        return ticketService.findTickets(currentPage, itemsPerPage);
    }

    @GET
    @Path("/by-date-descending")
    public Response findAllTicketByDateDescending() {
        return this.ticketService.findAllTicketByDateDescending();
    }

    @GET
    @Path("/{code}")
    public Response findTicketByCode(@PathParam("code") String code) {
        return ticketService.findTicketByCode(code);
    }

    @GET
    @Path("/status-ticket/{code}")
    public Response findTicketStatusByCode(@PathParam("code") String code) {
        return ticketService.findTicketStatusByCode(code);
    }

    @GET
    @Path("/by-email/{email}")
    public Response findTicketByEmail(@PathParam("email") String email) {
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

    @PATCH
    @Path("/update-ticket-nickname/{id}")
    public Response updateTicketNickname(@PathParam("id") Long id, String nickname) {
        return ticketService.updateTicketNickname(id, nickname);
    }

    @PATCH
    @Path("/update-ticket-email/{id}")
    public Response updateTicketEmail(@PathParam("id") Long id, String email) {
        return ticketService.updateTicketEmail(id, email);
    }

    @PATCH
    @Path("/update-ticket-phone/{id}")
    public Response updateTicketPhone(@PathParam("id") Long id, String phone) {
        return ticketService.updateTicketPhone(id, phone);
    }

    @PATCH
    @Path("/update-ticket-description/{id}")
    public Response updateTicketDescription(@PathParam("id") Long id, String description) {
        return ticketService.updateTicketDescription(id, description);
    }

    @DELETE
    @Path("/all-delete")
    public Response deleteTickets() {
        return ticketService.deleteTickets();
    }
}
