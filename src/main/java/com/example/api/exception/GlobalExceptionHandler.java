package com.example.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IndexingException.class)
    public ResponseEntity<Object> handleIndexingException(IndexingException ex, WebRequest request) {
        BaseExceptionResponse exception = new BaseExceptionResponse(ex.getMessage(), ex.getHttpStatus());
        return new ResponseEntity<>(exception, ex.getHttpStatus());
    }

    @ExceptionHandler(SchemaException.class)
    public ResponseEntity<Object> handleSchemaException(SchemaException ex, WebRequest request) {
        BaseExceptionResponse exception = new BaseExceptionResponse(ex.getMessage(), ex.getHttpStatus());
        return new ResponseEntity<>(exception, ex.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
