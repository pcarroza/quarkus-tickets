package org.citadel.services.response;

import jakarta.ws.rs.core.Response;
import org.citadel.constants.HttpStatus;
import org.citadel.entities.DTOs.SendStatusTicketDTO;
import org.citadel.entities.enums.TicketStatus;

public class ResponseActivated {

    private final Report report;

    public ResponseActivated() {
        report = new Report()
                .put("statusCode", HttpStatus.OK)
                .put("message", "The Ticket has been activated.");
    }

    public Response build() {
        report.put("payload",new SendStatusTicketDTO(TicketStatus.ACTIVATED));
        return Response.ok(report.getReport()).build();
    }
}
