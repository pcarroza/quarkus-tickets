package org.citadel.services.response;

import jakarta.ws.rs.core.Response;
import org.citadel.constants.HttpStatus;

public enum ResponseWrapper {

    SEED(HttpStatus.CREATED, "Se a cargado la base de datos..."),
    NOT_FOUND_BY_EMAIL(HttpStatus.NOT_FOUND,"El ticket con email '%s' no ha sido encontrado."),
    NOT_FOUND_BY_CODE(HttpStatus.NOT_FOUND,"El ticket con código '%s' no ha sido encontrado."),
    NOT_FOUND_BY_ID(HttpStatus.NOT_FOUND, "El ticket con ID: '%d' no ha sido encontrado."),
    NOT_FOUND_TICKETS(HttpStatus.NOT_FOUND, "No hay información para mostrar."),
    NO_CONTENT(HttpStatus.NO_CONTENT, "El ticket con '%s' a sido eliminado exitosamente."),
    DELETE_ALL(HttpStatus.NO_CONTENT, ""),
    CREATED(HttpStatus.CREATED, "El ticket a sido crado exitosamente."),
    SUCCESS(HttpStatus.OK, "Operación exitosa."),
    CANCELLED(HttpStatus.OK, "El ticket a sido cancelado."),
    ACTIVATED(HttpStatus.OK, "El ticket está vigente.");

    private final int status;

    private final String message;

    ResponseWrapper(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public <T> Response build(T entity) {
        return Response.status(status).entity(entity).build();
    }

    public Response build(String param) {
        String message = String.format(this.message, param);
        return Response.status(status).entity(message).build();
    }

    public Response build() {
        return Response.status(status).entity(this.message).build();
    }
}
