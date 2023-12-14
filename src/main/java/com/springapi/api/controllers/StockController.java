package com.springapi.api.controllers;

import com.springapi.api.common.exceptions.StockNotFoundException;
import com.springapi.api.models.Stock;
import com.springapi.api.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping("/{ticker}") // e.g. /api/stocks/AAPL
    public Stock getStockDetails(@PathVariable String ticker) {
        return stockService.getStockDetails(ticker)
                .orElseThrow(() -> new StockNotFoundException("Stock with ticker:" + ticker + " not found"));
    }


    // todo Other endpoints for historical data, multiple stocks, etc.
}
