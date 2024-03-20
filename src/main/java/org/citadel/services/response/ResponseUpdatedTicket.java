package org.citadel.services.response;

import jakarta.ws.rs.core.Response;
import org.citadel.constants.HttpStatus;

public class ResponseUpdatedTicket {

    private final Report report;

    public ResponseUpdatedTicket() {
        report = new Report()
                .put("statusCode", HttpStatus.OK)
                .put("message", "The Ticket update has been successful");
    }

    public Response build() {
        return Response.ok(report.getReport()).build();
    }
}
