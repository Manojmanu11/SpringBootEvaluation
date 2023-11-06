package com.example.SpringEvaluation.exceptionHandler;

public class ArgumentConstraintViolation extends RuntimeException{
    public ArgumentConstraintViolation(String message) {
        super(message);
    }
}
