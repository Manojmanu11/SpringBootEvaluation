package com.example.SpringEvaluation.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TradeHistoryDto {
    private String stockSymbol;
    private int quantity;
    private double price;
    private String orderType;
    private String orderStatus;
    private LocalDateTime timestamp;
}
