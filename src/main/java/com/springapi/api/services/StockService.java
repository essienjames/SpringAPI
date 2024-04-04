package com.springapi.api.services;

import com.springapi.api.common.exceptions.StockNotFoundException;
import com.springapi.api.controllers.StockController;
import com.springapi.api.models.Stocks;
import com.springapi.api.repositories.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class StockService {
    private final StockRepository stockRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(StockController.class);

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<Stocks> findAll() {
        return stockRepository.findAll();
    }

    public Stocks findByTicker(String ticker) {
        LOGGER.info("Fetching stock with ticker: {}", ticker);
        return stockRepository.findByTicker(ticker)
                .orElseThrow(() -> new StockNotFoundException("Stock with ticker: " + ticker + " not found"));
    }

    public List<Stocks> findByStockExchange(String stockExchange) {
        LOGGER.info("Fetching all stocks by stock exchange: {}", stockExchange);

        // Check if the stockExchange code is valid before querying the repository
        if (!isValidStockExchange(stockExchange)) {
            throw new StockNotFoundException("Invalid market identifier code: " + stockExchange);
        }

        // Check if stockExchange is empty
        if (stockExchange.isEmpty()) {
            throw new StockNotFoundException("Market identifier code cannot be empty");
        }

        return stockRepository.findByStockExchange(stockExchange);
    }

    private boolean isValidStockExchange(String stockExchange) {
        List<String> validMarketIdentifierCodes = Arrays.asList(
                "NYSE", "NASDAQ", "XAMS", "XBRU", "XPAR", "GPW",
                "XJPX", "XHKG", "XTSE", "XLON", "XFRA", "XKOS"
        );

        return validMarketIdentifierCodes.contains(stockExchange);
    }

    public List<Stocks> findByCurrency(String currency) {
        LOGGER.info("Fetching all stocks by currency: {}", currency);
        return stockRepository.findByCurrency(currency);
    }

    public List<Stocks> findByType(String type) {
        LOGGER.info("Fetching all stocks by type: {}", type);
        return stockRepository.findByType(type);
    }

    public Stocks createStock(Stocks stock) {
        LOGGER.info("Adding new stock: {}", stock);
        Stocks existingStock = stockRepository.findByTicker(stock.getTicker()).orElse(null);

        if (existingStock != null) {
            throw new IllegalArgumentException("Stock with ticker: " + stock.getTicker() + " already exists");
        }

        return stockRepository.save(stock);
    }

    public Stocks updateStock(String ticker, Stocks stock) {
        LOGGER.info("Updating stock with ticker: {}", ticker);
        Stocks existingStock = stockRepository.findByTicker(ticker)
                .orElseThrow(() -> new StockNotFoundException("Stock with ticker:" + ticker + " not found"));

        // Update stock details
        existingStock.setPrice(stock.getPrice());
        existingStock.setCompany(stock.getCompany());
        existingStock.setCurrency(stock.getCurrency());
        existingStock.setStockExchange(stock.getStockExchange());
        existingStock.setType(stock.getType());

        return stockRepository.save(existingStock);
    }

    public Stocks deleteStock(String ticker) {
        LOGGER.info("Deleting stock with ticker: {}", ticker);
        Stocks existingStock = stockRepository.findByTicker(ticker)
                .orElseThrow(() -> new StockNotFoundException("Stock with ticker:" + ticker + " not found"));
        stockRepository.delete(existingStock);
        return existingStock;
    }
}
