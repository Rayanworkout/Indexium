package com.github.rayanworkout.api.exception;

import org.springframework.http.HttpStatus;

public class SearchException extends CustomException {
    public SearchException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
