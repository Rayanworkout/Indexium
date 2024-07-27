package com.example.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IndexingException.class)
    public ResponseEntity<Object> handleIndexingException(BaseException ex, WebRequest request) {
        IndexingException exception = new IndexingException(ex.getMessage(), ex.getHttpStatus());
        return new ResponseEntity<>(exception, ex.getHttpStatus());
    }

    @ExceptionHandler(SchemaException.class)
    public ResponseEntity<Object> handleSchemaException(BaseException ex, WebRequest request) {
        SchemaException exception = new SchemaException(ex.getMessage(), ex.getHttpStatus());
        return new ResponseEntity<>(exception, ex.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
