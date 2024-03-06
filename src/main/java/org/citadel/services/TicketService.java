package org.citadel.services;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response;
import org.citadel.entities.DTOs.SendStatusTicketDTO;
import org.citadel.entities.DTOs.SendTicketDTO;
import org.citadel.entities.DTOs.UpdateTicketDTO;
import org.citadel.entities.Ticket;
import org.citadel.entities.DTOs.CreateTicketDTO;
import org.citadel.entities.enums.TicketStatus;
import org.citadel.services.response.PaginatedResponse;
import org.citadel.services.response.ResponseWrapper;
import org.citadel.services.response.factory.ResponseFactory;
import org.citadel.services.response.factory.ResponseNotFound;
import org.citadel.services.response.factory.ResponseNotFoundTicketByCode;
import org.citadel.services.response.factory.ResponseSuccess;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@ApplicationScoped
@Transactional
public class TicketService {

    private static final String EMAIL = "email";

    private static final String CODE = "code";

    private static final String ID = "id";

    private final ResponseFactory responseFactory;

    @Inject
    public TicketService(ResponseFactory responseFactory) {
        this.responseFactory = responseFactory;
    }

    public Response seed(List<CreateTicketDTO> createTicketDTOs) {
        createTicketDTOs.forEach(createTicketDTO -> {
            Ticket ticket = createTicketDTO.createTicket();
            ticket.persist();
        });
        return ResponseWrapper.SEED.build();
    }

    public Response createTicket(@Valid CreateTicketDTO createTicketDto) {
        Ticket ticket = createTicketDto.createTicket();
        ticket.persist();
        return new ResponseSuccess().build(ticket);
    }

    public Response activateTicket(String code) {
        return findTicketBy(CODE, () -> code)
                .map(ticket -> {
                    if (ticket.getStatus() == TicketStatus.GENERATED) {
                        ticket.setStatus(TicketStatus.ACTIVATED);
                        ticket.persist();
                        return responseFactory.getResponseSuccess();
                    } else if (ticket.getStatus() == TicketStatus.ACTIVATED) {
                        return ResponseWrapper.ACTIVATED.build();
                    } else {
                        return ResponseWrapper.CANCELLED.build();
                    }
                }).orElse(ResponseWrapper.NOT_FOUND_BY_CODE.build(code));
    }

    public Response findTickets(int currentPage, int itemsPerPage) {
        PanacheQuery<Ticket> panacheQuery = new Ticket().findPaginatedTickets(currentPage, itemsPerPage);
        if (panacheQuery.count() != 0) {
            return responseFactory.getResponseSuccess(new PaginatedResponse<>(panacheQuery));
        }
        return responseFactory.getResponseNotFound();
    }

    public Response findAllTicketByDateDescending() {
        List<Ticket> tickets = Ticket.listAll(Sort.by("createdAt").descending());
        if (!tickets.isEmpty()) {
            return ResponseWrapper.SUCCESS.build(tickets);
        }
        return new ResponseNotFound().build();
    }

    public Response findTicketStatusByCode(String code) {
        return findTicketBy(CODE, () -> code)
                .map(ticket -> new ResponseSuccess().build(new SendStatusTicketDTO(ticket.getStatus())))
                .orElse(new ResponseNotFoundTicketByCode().build(code));
    }

    public Response findTicketByCode(String code) {
        return findTicketBy(CODE, () -> code)
                .map(ticket -> ResponseWrapper.SUCCESS.build(new SendTicketDTO(ticket)))
                .orElse(new ResponseNotFoundTicketByCode().build(code));
    }

    public Response findTicketByEmail(String email) {
        return findTicketBy(EMAIL, () -> email)
                .map(ResponseWrapper.SUCCESS::build)
                .orElse(ResponseWrapper.NOT_FOUND_BY_EMAIL.build(email));
    }

    public Response deleteTicketByCode(String code) {
        Ticket.delete(CODE, code);
        return ResponseWrapper.NO_CONTENT.build();
    }

    public Response updateTicket(Long id, UpdateTicketDTO ticketDTO) {
        return findTicketBy(ID, () -> id)
                .map(ticket -> {
                    ticket.setNickname(ticketDTO.getNickname());
                    ticket.setEmail(ticketDTO.getEmail());
                    ticket.setPhone(ticketDTO.getPhone());
                    ticket.setDescription(ticketDTO.getDescription());
                    ticket.setStatus(ticketDTO.getStatus());
                    ticket.setUpdateAt();
                    ticket.persist();
                    return ResponseWrapper.SUCCESS.build(ticket);
                }).orElse(ResponseWrapper.NOT_FOUND_BY_ID.build(id));
    }

    private <T> Optional<Ticket> findTicketBy(String query, Supplier<T> param) {
        return Optional.ofNullable(Ticket.find(query, param.get()).firstResult());
    }

    public Response updateTicketNickname() {
        return null;
    }

    public Response updateTicketEmail() {
        return null;
    }

    public Response updateTicketPhone() {
        return null;
    }

    public Response updateTicketDescription() {
        return null;
    }

    public Response deleteTickets() {
        Ticket.deleteAll();
        return ResponseWrapper.DELETE_ALL.build();
    }
}
