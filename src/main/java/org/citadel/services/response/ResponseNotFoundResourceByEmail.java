package org.citadel.services.response;

import jakarta.ws.rs.core.Response;
import org.citadel.constants.HttpStatus;

public class ResponseNotFoundResourceByEmail {

    private final Report report;

    private final int statusCode = HttpStatus.NOT_FOUND;

    public ResponseNotFoundResourceByEmail() {
        report = new Report().put("statusCode", statusCode);
    }

    public Response build(String param) {
        var message = "The ticket with email '%s' has not been found";
        report.put("message", String.format(message, param));
        return Response.status(statusCode).entity(report.getReport()).build();
    }
}
