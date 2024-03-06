package org.citadel.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.citadel.constants.HttpStatus;

import java.util.NoSuchElementException;

@Provider
public class NoSuchElementoExceptionMapper implements ExceptionMapper<NoSuchElementException> {

    private record NoSuchElementExceptionMessage(String title, String detail) {
    }

    @Override
    public Response toResponse(NoSuchElementException exception) {
        var error = new NoSuchElementExceptionMessage(exception.getMessage(), null);
        return Response.status(HttpStatus.NOT_FOUND).entity(error).build();
    }
}
