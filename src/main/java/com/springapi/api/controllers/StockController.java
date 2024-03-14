package com.springapi.api.controllers;

import com.springapi.api.models.Stocks;
import com.springapi.api.services.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockController {

    private final StockService stockService;
    private static final Logger LOGGER = LoggerFactory.getLogger(StockController.class);

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping()
    public List<Stocks> getAllStocks() {
            LOGGER.info("Fetching all stocks");
            if (stockService.findAll().isEmpty()) {
                LOGGER.warn("Stock list empty, no stocks found");
            } else {
                LOGGER.info("Returning all stocks");
            }
        return stockService.findAll(); // This will retrieve all entities from the database
    }

    @GetMapping("/{ticker}") // e.g. /stocks/AAPL
    public ResponseEntity<?> getStockDetails(@PathVariable(name = "ticker") String ticker) {
        Stocks stock = stockService.findByTicker(ticker);
        LOGGER.info("Stock found with ticker: {}", ticker);
        return ResponseEntity.ok(stock);
    }

    // todo Other endpoints for:
    //  add stock,
    //  update stock,
    //  delete stock,
    //  historical data (e.g. prices between two dates),
    //  fetch all stocks by stock_exchange/currency/type, etc.
}
