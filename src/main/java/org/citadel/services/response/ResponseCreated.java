package org.citadel.services.response;

import jakarta.ws.rs.core.Response;
import org.citadel.constants.HttpStatus;

public class ResponseCreated {

    private final Report report;

    public ResponseCreated() {
        report = new Report()
                .put("statusCode", HttpStatus.OK)
                .put("message", "The Ticket has been created successfully");
    }

    public Response build() {
        return Response.ok(report.getReport()).build();
    }
}
