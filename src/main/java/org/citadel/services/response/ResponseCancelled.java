package org.citadel.services.response;

import jakarta.ws.rs.core.Response;
import org.citadel.constants.HttpStatus;
import org.citadel.entities.DTOs.SendStatusTicketDTO;
import org.citadel.entities.enums.TicketStatus;

public class ResponseCancelled {

    private final Report report;

    public ResponseCancelled() {
        report = new Report()
                .put("statusCode", HttpStatus.OK)
                .put("message", "The ticket has been cancelled");
    }

    public Response build() {
        report.put("payload", new SendStatusTicketDTO(TicketStatus.CANCELLED));
        return Response.ok(report.getReport()).build();
    }
}
