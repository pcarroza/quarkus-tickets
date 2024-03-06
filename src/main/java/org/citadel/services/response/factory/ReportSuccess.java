package org.citadel.services.response.factory;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ReportSuccess<T> extends Report {

    private T payload;

    public ReportSuccess(int statusCode, String message) {
        super(statusCode, message);
    }
}
