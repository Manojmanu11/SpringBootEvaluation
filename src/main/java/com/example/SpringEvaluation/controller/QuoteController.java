package com.example.SpringEvaluation.controller;

import com.example.SpringEvaluation.constants.ApiConstants;
import com.example.SpringEvaluation.dto.ResponseMessage;
import com.example.SpringEvaluation.entity.Symbol;
import com.example.SpringEvaluation.model.QuoteModel;
import com.example.SpringEvaluation.model.Response;
import com.example.SpringEvaluation.service.QuoteService;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@RestController
@RequestMapping(ApiConstants.Quote)
@Log4j2
public class QuoteController {
    @Autowired
    QuoteService quoteService;

    @PostMapping(ApiConstants.ImportSymbols)
    public Response saveCsvFile() throws IOException {
        return quoteService.storeCsvInDatabase();
    }

    @PostMapping(ApiConstants.GetSymbolDetails)               //Retrieve the symbol details from symbol table based on SYMBOL
    public List<Symbol> getSymbolDetails(@RequestBody @NotNull QuoteModel request) {
        String symbolName = request.getSymbolName();
        return quoteService.getSymbol(symbolName);
    }


    @GetMapping(ApiConstants.GetAllSymbols)       //Retrieve all symbols details
    public List<Symbol> getAllSymbolDetails() {
        return quoteService.getAllSymbols();
    }
}
