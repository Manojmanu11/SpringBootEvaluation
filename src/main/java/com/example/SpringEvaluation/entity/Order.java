package com.example.SpringEvaluation.entity;


import com.example.SpringEvaluation.constants.StringConstants;
import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDetail userOrdersId;

    @NotBlank(message = StringConstants.StockSymbolNotBlank)
    @Size(min = 10, max = 25, message = StringConstants.StockSymbolLength)
    private String stockSymbol;
    private String assertType = "SHARES";

    @Pattern(regexp = "(?i)buy|sell",message = StringConstants.OrderType)
    private String orderType;

    @Positive(message =StringConstants.PricePositive)
    @DecimalMin(value = "0.0", message = StringConstants.PriceConstraint)
    private double price;

    @Digits(integer = Integer.MAX_VALUE, fraction = 0, message = StringConstants.QuantityConstraint)
    private int quantity;
    @Column(name = "status")
    private String status;

    private LocalDateTime timestamp;
}