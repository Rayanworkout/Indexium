package com.example.api.exception;

import org.springframework.http.HttpStatus;

public class BaseExceptionResponse {

    private HttpStatus status;
    private String message;
    private Object example;

    public BaseExceptionResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public BaseExceptionResponse(String message, HttpStatus status, Object example) {
        this.message = message;
        this.status = status;
        this.example = example;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status.toString();
    }

    public Object getExample() {
        return example;
    }

}
