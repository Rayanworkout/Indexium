package com.example.api.exception;

import org.springframework.http.HttpStatus;

public class IndexingException extends CustomException {
    public IndexingException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
