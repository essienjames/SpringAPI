package com.springapi.api.controllers;

import com.springapi.api.common.exceptions.StockNotFoundException;
import com.springapi.api.models.Stocks;
import com.springapi.api.repositories.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    private StockRepository stockRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(StockController.class);

    @GetMapping("/get-all")
    public List<Stocks> getAllStocks() {
            LOGGER.info("Fetching all stocks");
            if (stockRepository.findAll().isEmpty()) {
                LOGGER.warn("No stocks found");
            } else {
                LOGGER.info("Returning all stocks");
            }
        return stockRepository.findAll(); // This will retrieve all entities from the database
    }

    @GetMapping("/{ticker}") // e.g. /stocks/AAPL
    public ResponseEntity<?> getStockDetails(@PathVariable(name = "ticker") String ticker) {
        LOGGER.info("Fetching stock with ticker: {}", ticker);
        if (ticker == null) {
            LOGGER.warn("Invalid ticker provided");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ticker provided");
        } else {
            try {
                Stocks stock = stockRepository.findByTicker(ticker)
                        .orElseThrow(() -> new StockNotFoundException("Stock with ticker:" + ticker + " not found"));
                LOGGER.info("Stock found with ticker: {}", ticker);
                return ResponseEntity.ok(stock);
            } catch (StockNotFoundException exception) {
                LOGGER.warn("Stock not found with ticker: {}", ticker);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Stock not found with ticker: " + ticker);
            }
        }
    }

    // todo Other endpoints for historical data, multiple stocks, etc.
}
