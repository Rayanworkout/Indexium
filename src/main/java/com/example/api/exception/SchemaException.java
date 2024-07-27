package com.example.api.exception;

import org.springframework.http.HttpStatus;

public class SchemaException extends BaseException {
    public SchemaException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
