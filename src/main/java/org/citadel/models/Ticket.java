package org.citadel.models;

import java.util.Objects;

public class Ticket {

    private int code;

    private String email;

    private String status;

    public Ticket(int code, String email, String status) {
        this.code = code;
        this.email = email;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public boolean has(int code) {
        return this.code == code;
    }

    public boolean has(String email) {
        return Objects.equals(this.email, email);
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
