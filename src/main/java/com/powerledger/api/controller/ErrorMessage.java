package com.powerledger.api.controller;

import java.util.Date;

public class ErrorMessage {
    private Date timestamp;
    private String message;
    private String errors;

    public ErrorMessage(Date timestamp, String message, String errors) {
        this.timestamp = timestamp;
        this.message = message;
        this.errors = errors;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getErrors() {
        return errors;
    }
}
