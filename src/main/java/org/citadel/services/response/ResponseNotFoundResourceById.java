package org.citadel.services.response;

import jakarta.ws.rs.core.Response;
import org.citadel.constants.HttpStatus;

public class ResponseNotFoundResourceById {

    private final Report report;

    private final int statusCode = HttpStatus.NOT_FOUND;

    public ResponseNotFoundResourceById() {
        report = new Report().put("statusCode", statusCode);
    }

    public Response build(Long id) {
        var message = "El ticket con ID: '%d' no ha sido encontrado.";
        report.put("message", String.format(message, id));
        return Response.status(statusCode).entity(report.getReport()).build();
    }
}
