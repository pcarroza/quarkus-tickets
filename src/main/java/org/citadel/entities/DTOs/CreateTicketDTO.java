package org.citadel.entities.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.citadel.entities.Ticket;

@Data
public class CreateTicketDTO {

    String nickname = "None";

    @NotBlank(message = "El campo 'email' no puede estar en blanco.")
    @Email(message = "El 'emial' debe cumplir con el format.")
    String email;

    String phone;

    String description = "None";

    public Ticket createTicket() {
        Ticket ticket = new Ticket();
        ticket.setNickname(nickname);
        ticket.setEmail(email);
        ticket.setPhone(phone);
        ticket.setDescription(description);
        return ticket;
    }
}
