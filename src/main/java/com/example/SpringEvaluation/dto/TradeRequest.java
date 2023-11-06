package com.example.SpringEvaluation.dto;

import com.example.SpringEvaluation.entity.Order;
import lombok.Data;

@Data
public class TradeRequest {
    String groupName;
    String symbolName;
    Long groupId;
    AddSymbol symbol;
    Order order;
    String orderOperation;
    String orderId;
}
