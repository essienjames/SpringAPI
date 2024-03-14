package com.springapi.api.services;

import com.springapi.api.common.exceptions.StockNotFoundException;
import com.springapi.api.controllers.StockController;
import com.springapi.api.models.Stocks;
import com.springapi.api.repositories.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
                .orElseThrow(() -> new StockNotFoundException("Stock with ticker:" + ticker + " not found"));
    }
}
