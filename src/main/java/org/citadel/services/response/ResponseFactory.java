package org.citadel.services.response;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class ResponseFactory {

    public Response getResponseNotFound() {
        return new ResponseNotFound().build();
    }

    public Response getResponseNotFoundTicketByCode(String param) {
        return new ResponseNotFoundResourceByCode().build(param);
    }

    public <T> Response getResponseSuccess(T payload) {
        return new ResponseSuccess().build(payload);
    }

    public Response getResponseSuccess() {
        return new ResponseSuccess().build();
    }

    public Response getResponseTicketActivated() {
        return new ResponseActivated().build();
    }

    public Response getResponseTicketProcessed() {
        return new ResponseTicketProcessed().build();
    }

    public Response getResponseTicketCancelled() {
        return new ResponseCancelled().build();
    }

    public Response getResponseNotFoundTicketByEmail(String param) {
        return new ResponseNotFoundResourceByEmail().build(param);
    }

    public Response getResponseNotFoundTicketById(Long id) {
        return new ResponseNotFoundResourceById().build(id);
    }
}


