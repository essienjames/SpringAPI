package com.springapi.tests.unit.services;

import com.springapi.api.controllers.StockController;
import com.springapi.api.models.Stocks;
import com.springapi.api.services.StockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasItems;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit tests for the StockService class.
 *
 */
@WebMvcTest(StockController.class)
@ExtendWith(MockitoExtension.class)
public class StockServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StockService stockService;

    private List<Stocks> mockStocks;

    @BeforeEach
    public void setUp() {
        mockStocks = Arrays.asList(
                new Stocks("AAPL", "Apple Inc.", 150, "USD", "NASDAQ", "Technology"),
                new Stocks("GOOGL", "Alphabet Inc.", 2000, "USD", "NASDAQ", "Technology"),
                new Stocks("AMZN", "Amazon.com Inc.", 3000, "USD", "NASDAQ", "Technology")
        );
    }

    /**
     * Verifies the behavior of the findAll() method in the StockService.
     */
    @Test
    public void testFindAll_ShouldReturnAllStocks() throws Exception {
        // given
        given(stockService.findAll()).willReturn(mockStocks);

        // when
        ResultActions resultActions = mockMvc.perform(get("/stocks")
                .contentType("application/json"));

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[*].ticker").value(hasItems("AAPL", "GOOGL", "AMZN")))
                .andExpect(jsonPath("$[*].company").value(hasItems("Apple Inc.", "Alphabet Inc.", "Amazon.com Inc.")))
                .andExpect(jsonPath("$[*].price").value(hasItems(150, 2000, 3000)))
                .andExpect(jsonPath("$[*].currency").value(hasItems("USD")))
                .andExpect(jsonPath("$[*].stockExchange").value(hasItems("NASDAQ")))
                .andExpect(jsonPath("$[*].type").value(hasItems("Technology")));
    }

    /**
     * Ensures that the findByTicker() method correctly retrieves a stock by its ticker
     * and that the returned data matches the expected format and values.
     */
    @Test
    public void testFindByTicker_ShouldReturnStockByTicker() throws Exception {
        // given
        Stocks stock = mockStocks.get(0);
        String validTicker = "AAPL";
        given(stockService.findByTicker(validTicker)).willReturn(stock);

        // when
        ResultActions resultActions = mockMvc.perform(get("/stocks/" + validTicker));

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.ticker").value("AAPL"))
                .andExpect(jsonPath("$.company").value("Apple Inc."))
                .andExpect(jsonPath("$.price").value(150))
                .andExpect(jsonPath("$.currency").value("USD"))
                .andExpect(jsonPath("$.stockExchange").value("NASDAQ"))
                .andExpect(jsonPath("$.type").value("Technology"));
    }

    /**
     * Ensures that the findByTicker() method returns a 404 Not Found status
     * when a stock with the specified ticker is not found.
     */

    @Test
    public void testFindByTicker_ShouldReturnStockNotFound() throws Exception {
        // given
        String invalidTicker = "XYZ";
        given(stockService.findByTicker(invalidTicker)).willReturn(null);

        // when
        ResultActions resultActions = mockMvc.perform(get("/stocks/" + invalidTicker));

        // then
        resultActions.andExpect(status().isNotFound());
    }

    /**
     * Ensures that the findByStockExchange() method correctly retrieves all stock
     * by the specified stock exchange.
     */
    @Test
    public void testFindByStockExchange_ShouldReturnStocksByStockExchange() throws Exception {
        // given
        String validStockExchange = "NASDAQ";
        given(stockService.findByStockExchange(validStockExchange)).willReturn(mockStocks);

        // when
        ResultActions resultActions = mockMvc.perform(get("/stocks/stock_exchange/" + validStockExchange));

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[*].stockExchange").value(hasItems("NASDAQ")));
    }

    /**
     * Ensures the findByStockExchange() method returns an empty list
     * when no stocks are found for the specified stock exchange.
     */
    @Test
    public void testFindByStockExchange_ShouldReturnExchangeNotFound() throws Exception {
        // given
        String invalidStockExchange = "";
        given(stockService.findByStockExchange(invalidStockExchange)).willReturn(null);

        // when
        ResultActions resultActions = mockMvc.perform(get("/stocks/stock_exchange/" + invalidStockExchange));

        // then
        resultActions.andExpect(status().isNotFound());
    }
}
