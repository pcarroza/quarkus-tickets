package org.citadel.services.response;

import jakarta.ws.rs.core.Response;
import org.citadel.constants.HttpStatus;

public class ResponseNotFoundResourceByCode {

    private final Report report;

    public ResponseNotFoundResourceByCode() {
        report = new Report().put("statusCode", HttpStatus.NOT_FOUND);
    }

    public Response build(String param) {
        var message = "The Ticket with code '%s' has not been found.";
        report.put("message", String.format(message, param));
        return Response.status(HttpStatus.NOT_FOUND).entity(report.getReport()).build();
    }
}
