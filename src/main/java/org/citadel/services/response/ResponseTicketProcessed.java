package org.citadel.services.response;

import jakarta.ws.rs.core.Response;
import org.citadel.constants.HttpStatus;
import org.citadel.entities.enums.TicketStatus;
import org.citadel.entities.DTOs.SendStatusTicketDTO;

public class ResponseTicketProcessed {

    private final Report report;

    private final int statusCode = HttpStatus.CONFLICT;

    public ResponseTicketProcessed() {
        report = new Report()
                .put("statusCode", HttpStatus.CONFLICT)
                .put("message", "The ticket has already been activated");
    }

    public Response build() {
        report.put("payload", new SendStatusTicketDTO(TicketStatus.PROCESSED));
        return Response.status(statusCode).entity(report.getReport()).build();
    }
}
