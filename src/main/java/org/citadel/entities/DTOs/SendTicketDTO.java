package org.citadel.entities.DTOs;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.citadel.entities.Ticket;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class SendTicketDTO extends UpdateTicketDTO {

    private Long id;

    private String code;

    private LocalDateTime createdAt;

    private LocalDateTime updateAt;

    public SendTicketDTO(Ticket ticket) {
        id = ticket.id;
        code = ticket.getCode();
        setNickname(ticket.getNickname());
        setEmail(ticket.getEmail());
        setStatus(ticket.getStatus());
        setDescription(ticket.getDescription());
        setCreatedAt(ticket.getCreatedAt());
        setUpdateAt(ticket.getUpdateAt());
    }
}
