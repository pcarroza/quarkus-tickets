package org.citadel.services;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response;
import org.citadel.constants.HttpStatus;
import org.citadel.entities.DTOs.*;
import org.citadel.entities.Ticket;
import org.citadel.services.response.ResponseFactory;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

@ApplicationScoped
@Transactional
public class TicketService {

    private static final String EMAIL = "email";

    private static final String CODE = "code";

    private static final String ID = "id";

    private final ResponseFactory factory;

    @Inject
    public TicketService(ResponseFactory responseFactory) {
        factory = responseFactory;
    }

    public Response loadTickets(List<CreateTicketDTO> createTicketDTOs) {
        createTicketDTOs.forEach(createTicketDTO -> {
            Ticket ticket = createTicketDTO.createTicket();
            ticket.persist();
        });
        return Response.status(HttpStatus.CREATED).build();
    }

    public Response createTicket(@Valid CreateTicketDTO createTicketDto) {
        Ticket ticket = createTicketDto.createTicket();
        ticket.persist();
        return factory.getResponseSuccess();
    }

    public Response activateTicket(String code) {
        return findTicketBy(CODE, () -> code).map(ticket -> {
            if (ticket.isGenerated()) {
                ticket.setStatusActivated();
                ticket.persist();
                return factory.getResponseTicketActivated();
            } else if (ticket.isActivated()) {
                return factory.getResponseTicketProcessed();
            } else {
                return factory.getResponseTicketCancelled();
            }
        }).orElse(factory.getResponseNotFoundTicketByCode(code));
    }

    public Response findTickets(int currentPage, int itemsPerPage) {
        PanacheQuery<Ticket> panacheQuery = Ticket.findPaginatedTickets(currentPage, itemsPerPage);
        if (panacheQuery.count() != 0) {
            return factory.getResponseSuccess(new PaginatedResponse<>(panacheQuery));
        }
        return factory.getResponseNotFound();
    }

    public Response findAllTicketByDateDescending() {
        List<Ticket> tickets = Ticket.listAll(Sort.by("createdAt").descending());
        if (!tickets.isEmpty()) {
            return factory.getResponseSuccess(tickets);
        }
        return factory.getResponseNotFound();
    }

    public Response findTicketStatusByCode(String code) {
        return findTicketBy(CODE, () -> code)
                .map(ticket -> factory.getResponseSuccess(new SendStatusTicketDTO(ticket.getStatus())))
                .orElse(factory.getResponseNotFoundTicketByCode(code));
    }

    public Response findTicketByCode(String code) {
        return findTicketBy(CODE, () -> code)
                .map(ticket -> factory.getResponseSuccess(new SendTicketDTO(ticket)))
                .orElse(factory.getResponseNotFoundTicketByCode(code));
    }

    public Response findTicketByEmail(String email) {
        return findTicketBy(EMAIL, () -> email)
                .map(ticket -> factory.getResponseSuccess(new SendTicketDTO(ticket)))
                .orElse(factory.getResponseNotFoundTicketByEmail(email));
    }

    public Response updateTicket(Long id, UpdateTicketDTO ticketDTO) {
        return updateTicketByField(id, ticket -> ticket.updateTicketFrom(ticketDTO));
    }

    public Response updateTicketNickname(Long id, String nickname) {
        return updateTicketByField(id, ticket -> ticket.setNickname(nickname));
    }

    public Response updateTicketEmail(Long id, String email) {
        return updateTicketByField(id, ticket -> ticket.setEmail(email));
    }

    public Response updateTicketPhone(Long id, String phone) {
        return updateTicketByField(id, ticket -> ticket.setPhone(phone));
    }

    public Response updateTicketDescription(Long id, String description) {
        return updateTicketByField(id, ticket -> ticket.setDescription(description));
    }

    private Response updateTicketByField(Long id, Consumer<Ticket> consumer) {
        return findTicketBy(ID, () -> id).map(ticket -> {
            consumer.accept(ticket);
            ticket.setUpdateAt();
            ticket.persist();
            return factory.getResponseSuccess();
        }).orElse(factory.getResponseNotFoundTicketById(id));
    }

    private <T> Optional<Ticket> findTicketBy(String query, Supplier<T> param) {
        return Optional.ofNullable(Ticket.find(query, param.get()).firstResult());
    }

    public Response deleteTicketByCode(String code) {
        Ticket.delete(CODE, code);
        return Response.noContent().build();
    }

    public Response deleteTickets() {
        Ticket.deleteAll();
        return Response.noContent().build();
    }
}
