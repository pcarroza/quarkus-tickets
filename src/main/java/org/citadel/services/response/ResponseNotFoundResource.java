package org.citadel.services.response;

import jakarta.ws.rs.core.Response;
import org.citadel.constants.HttpStatus;

public class ResponseNotFoundResource {

    private final Report report;

    public ResponseNotFoundResource() {
        report = new Report()
                .put("statusCode", HttpStatus.NOT_FOUND)
                .put("message", "The Ticket has not been found.");
    }

    public Response build() {
        return Response.status(HttpStatus.NOT_FOUND).entity(report.getReport()).build();
    }
}

