package com.springapi.api.services;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.springapi.api.models.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StockService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockService.class);
    private final List<Stock> stockList = new ArrayList<>();

    public StockService() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("static/stocksData.csv")).getFile());
        String csvFile = file.getAbsolutePath();

        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            String[] column;
            while ((column = reader.readNext()) != null) {

                // Process each line from the CSV file
                String ticker = column[0];
                String companyName = column[1];
                String price = column[2];
                String currency = column[3];
                String stockExchange = column[4];
                String type = column[5];

                // Create Stock object and store data in the list
                Stock stock = new Stock(ticker, companyName, price, currency, stockExchange, type);
                stockList.add(stock);
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
            // todo Handle exception appropriately
        }
    }

    public Optional<Stock> getStockDetails(String ticker) {
        if (stockList.isEmpty()) {
            LOGGER.warn("Stock list is empty");
            return Optional.empty();
        }

        LOGGER.info("Searching for stock with ticker: {}", ticker);
        Optional<Stock> foundStock = stockList.stream()
                .filter(it -> ticker.equals(it.getTicker()))
                .findFirst();

        if (foundStock.isPresent()) {
            LOGGER.info("Stock found with ticker: {}", ticker);
        } else {
            LOGGER.warn("Stock not found with ticker: {}", ticker);
        }

        return foundStock;
    }

    // todo Other methods for historical data, multiple stocks, etc.

//    public void addOrUpdateStock(Stock stock) {
//        stockRepository.save(stock);
//        // perform additional logic here if needed
//    }

//    public void updateStock(Stock stock) {
//        stockRepository.update(stock);
//        // Additional logic, validation, etc.
//    }

//    public void deleteStockBySymbol(String ticker) {
//        stockRepository.deleteByTicker(ticker);
//        // Additional logic, validation, etc.
//    }

//    public void addStock(Stock newStock) {
//        stockRepositoryList.add(newStock);
//        // Implement validation or error handling here
//    }

//    public void updateStock(Stock updatedStock) {
//        stockRepositoryList.stream()
//                .filter(stock -> updatedStock.getSymbol().equals(stock.getSymbol()))
//                .findFirst()
//                .ifPresent(stock -> {
//                    stock.setCompanyName(updatedStock.getCompanyName());
//                    stock.setPrice(updatedStock.getPrice());
//                    // Update other attributes as needed
//                });
//        // Handle cases where the stock isn't found
//    }

//    public void deleteStock(String symbol) {
//        stockRepositoryList.removeIf(stock -> symbol.equals(stock.getSymbol()));
//        // Handle cases where the stock isn't found
//    }

//    public List<Stock> getAllStocks() {
//        return new ArrayList<>(stockRepositoryList);
//    }

//    public double calculateStockPerformance(String symbol) {
//        // Implement logic to calculate stock performance
//    }
}
