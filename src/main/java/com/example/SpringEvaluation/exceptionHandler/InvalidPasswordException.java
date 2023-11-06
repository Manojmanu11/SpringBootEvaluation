package com.example.SpringEvaluation.exceptionHandler;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
