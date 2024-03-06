package org.citadel.services.response.factory;

import lombok.Data;

@Data
public class Report {

    private int status;

    private String message;

    public Report(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public Report(int status) {
        this.status = status;
    }
}
