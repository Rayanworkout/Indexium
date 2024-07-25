package com.example.api.exception;

public class IndexingRequestException extends RuntimeException {
    public IndexingRequestException(String message) {
        super(message);
    }

    public IndexingRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
