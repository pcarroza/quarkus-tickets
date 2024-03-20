package org.citadel.services.response;

import jakarta.ws.rs.core.Response;
import org.citadel.constants.HttpStatus;

public class ResponseNotFound {

    private final Report report;

    public ResponseNotFound() {
        report = new Report()
                .put("statusCode", HttpStatus.NOT_FOUND)
                .put("message", "The resource is not found");
    }

    public Response build() {
        return Response.status(HttpStatus.NOT_FOUND).entity(report.getReport()).build();
    }
}
