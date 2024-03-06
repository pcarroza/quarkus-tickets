package org.citadel.services.response.factory;

import jakarta.ws.rs.core.Response;
import org.citadel.constants.HttpStatus;

public class ResponseNotFoundTickets {

    private final int status = HttpStatus.NOT_FOUND;

    private String message = "No hay información para mostrar.";

    public Response build() {
        return Response.status(status).build();
    }

    public <T> Response build(T payload) {
        return null;
    }
}

