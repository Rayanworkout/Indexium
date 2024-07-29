package com.example.api.dto;

import org.springframework.http.HttpStatus;

public class SuccessResponse {
    private String message;
    private HttpStatus httpStatus;

    public SuccessResponse(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHttpStatus() {
        return httpStatus.toString();
    }

}
