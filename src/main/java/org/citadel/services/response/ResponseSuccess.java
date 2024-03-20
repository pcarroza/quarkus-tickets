package org.citadel.services.response;

import jakarta.ws.rs.core.Response;
import org.citadel.constants.HttpStatus;

public class ResponseSuccess {

    private final Report report;

    public ResponseSuccess() {
        report = new Report()
                .put("statusCode", HttpStatus.OK)
                .put("message", "Successful");
    }

    public <T> Response build(T payload) {
        report.put("payload", payload);
        return Response.ok(report.getReport()).build();
    }

    public Response build() {
        return Response.ok(report.getReport()).build();
    }
}
