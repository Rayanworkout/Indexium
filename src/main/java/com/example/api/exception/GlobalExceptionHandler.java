package com.example.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IndexingRequestException.class)
    public ResponseEntity<Object> handleIndexingException(IndexingRequestException ex, WebRequest request) {

        HttpStatus badRequestStatus = HttpStatus.BAD_REQUEST;

        // Creating an IndexingException object
        IndexingException exception = new IndexingException(ex.getMessage(),
                badRequestStatus.toString());

        return new ResponseEntity<>(exception, badRequestStatus);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
