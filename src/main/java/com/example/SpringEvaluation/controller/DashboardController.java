package com.example.SpringEvaluation.controller;

import com.example.SpringEvaluation.constants.ApiConstants;
import com.example.SpringEvaluation.constants.StringConstants;
import com.example.SpringEvaluation.dto.*;
import com.example.SpringEvaluation.entity.Order;
import com.example.SpringEvaluation.model.OrderModel;
import com.example.SpringEvaluation.service.DashboardService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping(ApiConstants.Dashboard)
//"Manoj"
public class DashboardController {
    @Autowired
    DashboardService dashboardService;

    @PostMapping(ApiConstants.AddGroup)
    public ResponseEntity<String> addWatchlistGroup(@RequestBody TradeRequest tradeRequest) {
        log.info(StringConstants.ADDWATCHLISTGROUPSUCCESS);
        String response = dashboardService.addWatchlistGroup(tradeRequest);
        return ResponseEntity.status(HttpStatus.OK).header("contentType", "application/json").body(response);

    }
    @PostMapping(ApiConstants.AddSymbolToGroup)
    public ResponseEntity<UserDetailsDto> addSymbolToWatchListGroups(@RequestBody TradeRequest tradeRequest) {
        log.info(StringConstants.ADDSYMBOLTOWATCHLISTGROUPSSUCCESS);
        return ResponseEntity.ok((dashboardService.addSymbolToWatchlistGroups(tradeRequest)));
    }

    @GetMapping(ApiConstants.GetGroups)
    public ResponseEntity<List<WatchListGroupDto>> getWatchListGroups() {
        log.info(StringConstants.GETWATCHLISTGROUPSSUCCESS);

        return ResponseEntity.ok( dashboardService.getWatchlistGroups());
    }

    @GetMapping(ApiConstants.GetWatchlistSymbols)
    public ResponseEntity<List<String>>getSymbolsFromWatchListGroups(@RequestBody TradeRequest tradeRequest) {
        log.info(StringConstants.GETSYMBOLSFROMWATCHLISTGROUPSSUCCESS);
        return ResponseEntity.ok( dashboardService.getSymbolsFromWatchlistGroups(tradeRequest));

    }

    @PostMapping(ApiConstants.AddOrder)   // Adding order
    public ResponseMessage addOrder(@RequestBody Order order)
    {
        log.info("Adding the Order");
        return dashboardService.addOrder(order);
    }

    @PostMapping(ApiConstants.CancelOrder)
    public ResponseMessage cancelOrder(@RequestBody OrderModel tradeRequest)
    {
        log.info("Canceling the Order");
        return dashboardService.cancelOrder(tradeRequest);
    }
    @GetMapping(ApiConstants.GetPortfolio)
    public ResponseEntity<List<PortfolioItemDto>> getPortfolio() {
        log.info(StringConstants.PortfolioRetrievalSuccess);
        return ResponseEntity.ok(( dashboardService.getPortfolio()));
    }

    @GetMapping(ApiConstants.GetTradeHistory)
    public ResponseEntity<List<TradeHistoryDto>> getTradeHistory() {
        log.info(StringConstants.TradeHistoryRetrievalSuccess);
        return ResponseEntity.ok( dashboardService.getTradeHistory());
    }


}
