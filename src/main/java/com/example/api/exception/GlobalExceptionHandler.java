package com.example.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.dto.SetSchemaExample;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IndexingException.class)
    public ResponseEntity<Object> handleIndexingException(IndexingException ex, WebRequest request) {
        BaseExceptionResponse exception = new BaseExceptionResponse(ex.getMessage(), ex.getHttpStatus());
        return new ResponseEntity<>(exception, ex.getHttpStatus());
    }

    // SCHEMA NOT SET
    @ExceptionHandler(SchemaException.class)
    public ResponseEntity<Object> handleSchemaException(SchemaException ex, WebRequest request) {
        BaseExceptionResponse exception = new BaseExceptionResponse(ex.getMessage(), ex.getHttpStatus());
        return new ResponseEntity<>(exception, ex.getHttpStatus());
    }

    // INVALID PAYLOAD AT /SCHEMA/SET
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {

        String message = "To define a new schema, you need to have the 'fields' key in the body of your request, and then each field along with its type.";

        SetSchemaExample example = SetSchemaExample.getExampleSchema();

        BaseExceptionResponse exception = new BaseExceptionResponse(message, HttpStatus.BAD_REQUEST, example);
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
