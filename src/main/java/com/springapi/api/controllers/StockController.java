package com.springapi.api.controllers;

import com.springapi.api.models.Stocks;
import com.springapi.api.common.exceptions.DeleteStockException;
import com.springapi.api.services.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Stocks> addStock(@RequestBody Stocks stock) {
        LOGGER.info("Stock created: {}", stock);
        return ResponseEntity.ok(stockService.createStock(stock));
    }

    @PutMapping("/update/{ticker}")
    public ResponseEntity<Object> updateStock(@PathVariable(name = "ticker") String ticker, @RequestBody Stocks stock) {
        try {
            Stocks updatedStock = stockService.updateStock(ticker, stock);
            return ResponseEntity.ok(updatedStock);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/delete/{ticker}")
    public ResponseEntity<DeleteStockException> deleteStock(@PathVariable(name = "ticker") String ticker) {
        Stocks deletedStock = stockService.deleteStock(ticker);
        LOGGER.info("Stock deleted with ticker: {}", ticker);
        DeleteStockException response = new DeleteStockException("Stock with ticker " + ticker + " has been deleted", deletedStock);
        return ResponseEntity.ok(response);
    }

    // todo Other endpoints for:
    //  historical data (e.g. prices between two dates),
    //  fetch all stocks by stock_exchange/currency/type, etc.
}
