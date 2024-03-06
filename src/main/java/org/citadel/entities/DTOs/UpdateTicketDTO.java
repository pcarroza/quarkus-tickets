package org.citadel.entities.DTOs;

import lombok.Data;
import org.citadel.entities.enums.TicketStatus;

@Data
public class UpdateTicketDTO {

    private String code;

    private String nickname;

    private String email;

    private String phone;

    private TicketStatus status;

    private String description = "None";
}