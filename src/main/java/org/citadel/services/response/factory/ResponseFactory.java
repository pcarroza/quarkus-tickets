package org.citadel.services.response.factory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class ResponseFactory {

    public Response getResponseNotFound() {
        return new ResponseNotFound().build();
    }

    public <T> Response getResponseSuccess(T payload) {
        return new ResponseSuccess().build(payload);
    }

    public Response getResponseSuccess() {
        return new ResponseSuccess().build();
    }
}


