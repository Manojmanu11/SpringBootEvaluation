package com.example.SpringEvaluation.service;


import com.example.SpringEvaluation.dto.*;
import com.example.SpringEvaluation.entity.Order;
import com.example.SpringEvaluation.model.OrderModel;

import java.util.List;

public interface DashboardService {
    String addWatchlistGroup(TradeRequest tradeRequest);

    List<WatchListGroupDto> getWatchlistGroups();

    UserDetailsDto addSymbolToWatchlistGroups(TradeRequest tradeRequest);

    List<String> getSymbolsFromWatchlistGroups(TradeRequest tradeRequest);

     void executeOrders();
     List<PortfolioItemDto> getPortfolio();

     List<TradeHistoryDto> getTradeHistory();
     ResponseMessage addOrder(Order orders);
    ResponseMessage cancelOrder(OrderModel orderModel);
}
