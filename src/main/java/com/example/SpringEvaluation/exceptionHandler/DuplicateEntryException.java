package com.example.SpringEvaluation.exceptionHandler;

public class DuplicateEntryException extends RuntimeException {
    public DuplicateEntryException(String message) {
        super(message);
    }
}
