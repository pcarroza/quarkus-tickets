package org.citadel.services.response;

import jakarta.ws.rs.core.Response;
import org.citadel.constants.HttpStatus;

public class ResponseNotFoundResources {

    private final Report report;

    private final int statusCode = HttpStatus.NOT_FOUND;

    public ResponseNotFoundResources() {
        report = new Report()
                .put("statusCode", HttpStatus.NOT_FOUND)
                .put("message", "There is no information to display.");
    }

    public Response build() {
        return Response.status(statusCode).entity(report.getReport()).build();
    }
}
