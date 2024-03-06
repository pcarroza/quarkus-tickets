package org.citadel.services.response.factory;

import jakarta.ws.rs.core.Response;
import org.citadel.constants.HttpStatus;

public class ResponseNotFoundTicketByCode {

    private final Report report;

    private String message = "El ticket con código '%s' no ha sido encontrado.";

    public ResponseNotFoundTicketByCode() {
        report = new Report(HttpStatus.NOT_FOUND);
    }

    public Response build(String param) {
        String message = String.format(this.message, param);
        report.setMessage(message);
        return Response.status(HttpStatus.NOT_FOUND).entity(report).build();
    }

    public <T> Response build(T payload) {
        return null;
    }
}
