package com.example.SpringEvaluation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage<T> {
    private String message;
    private T Response;

    public ResponseMessage(T response) {
        Response = response;
    }

    public ResponseMessage(String message) {
        this.message = message;
    }
}
