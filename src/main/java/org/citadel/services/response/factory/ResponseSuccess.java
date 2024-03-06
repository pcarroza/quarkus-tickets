package org.citadel.services.response.factory;

import jakarta.ws.rs.core.Response;
import org.citadel.constants.HttpStatus;

public class ResponseSuccess  {

    private final ReportSuccess<Object> report;

    public ResponseSuccess() {
        this.report = new ReportSuccess<>(HttpStatus.OK, "Operación exitosa.");
    }

    public <T> Response build(T payload) {
        report.setPayload(payload);
        return build();
    }

    public Response build() {
        return Response.status(report.getStatus()).entity(report).build();
    }
}
