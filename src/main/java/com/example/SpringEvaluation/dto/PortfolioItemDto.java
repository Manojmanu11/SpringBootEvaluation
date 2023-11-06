package com.example.SpringEvaluation.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PortfolioItemDto {
    private String stockSymbol;
    private int quantity;
    private String orderStatus;
    private String assertType;
    private LocalDateTime timestamp;
}
