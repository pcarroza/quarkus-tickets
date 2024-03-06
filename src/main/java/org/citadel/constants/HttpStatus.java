package org.citadel.constants;

import jakarta.ws.rs.core.Response;

public class HttpStatus {

    public static final int OK = Response.Status.OK.getStatusCode();

    public static final int CREATED = Response.Status.CREATED.getStatusCode();

    public static final int NOT_FOUND = Response.Status.NOT_FOUND.getStatusCode();

    public static final int NO_CONTENT = Response.Status.NO_CONTENT.getStatusCode();

}
