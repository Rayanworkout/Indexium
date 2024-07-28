package com.example.api.exception;

import org.springframework.http.HttpStatus;

public class BaseExceptionResponse {

    private HttpStatus status;
    private String message;

    public BaseExceptionResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status.toString();
    }

}
