package org.citadel.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.citadel.entities.DTOs.UpdateTicketDTO;
import org.citadel.entities.enums.TicketStatus;

import java.time.LocalDateTime;

import static org.citadel.entities.utils.TicketCodeGenerator.generateUniqueCodeTicket;

@Getter
@Entity
public class Ticket extends PanacheEntity {

    @Column(unique = true)
    private String code;

    @Setter
    private String nickname;

    @Setter
    private String email;

    @Setter
    private String phone;

    @Setter
    private TicketStatus status;

    @Setter
    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updateAt;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public Ticket() {
        code = generateUniqueCodeTicket();
        status = TicketStatus.GENERATED;
        createdAt = LocalDateTime.now();
    }

    public void setUpdateAt() {
        updateAt = LocalDateTime.now();
    }

    public void updateTicketFrom(UpdateTicketDTO ticketDTO) {
        this.setNickname(ticketDTO.getNickname());
        this.setEmail(ticketDTO.getEmail());
        this.setPhone(ticketDTO.getPhone());
        this.setDescription(ticketDTO.getDescription());
        this.setStatus(ticketDTO.getStatus());
    }

    public boolean isGenerated() {
        return this.getStatus() == TicketStatus.GENERATED;
    }

    public boolean isActivated() {
        return this.getStatus() == TicketStatus.ACTIVATED;
    }

    public void setStatusActivated() {
        this.setStatus(TicketStatus.ACTIVATED);
    }

    public static PanacheQuery<Ticket> findPaginatedTickets(int indexOfPage, int numberOfElements) {
        Page page = new Page(indexOfPage - 1, numberOfElements);
        return Ticket.findAll(Sort.descending("createdAt")).page(page);
    }
}
