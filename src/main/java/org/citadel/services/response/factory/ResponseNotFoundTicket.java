package org.citadel.services.response.factory;

import jakarta.ws.rs.core.Response;
import org.citadel.constants.HttpStatus;

public class ResponseNotFoundTicket {

    private final Report report;

    public ResponseNotFoundTicket() {
        report = new Report(HttpStatus.NOT_FOUND, "El recurso no se encuentra");
    }

    public Response build() {
        return Response.status(HttpStatus.NOT_FOUND).entity(report).build();
    }
}

