package com.example.SpringEvaluation.entity;

import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "TRADE")
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDetail userTradeId;
    private String stockSymbol;
    private String orderType;
    private double price;
    private int quantity;
    private LocalDateTime timestamp;

}