package com.example.api.exception;

// import org.springframework.http.HttpStatus;

public class IndexingException {
    private final String message;
    private final String httpStatus;

    public IndexingException(String message, String httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public String getHttpStatus() {
        return httpStatus;
    }

}
