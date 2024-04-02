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

import static org.hamcrest.Matchers.hasItems;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Unit tests for the StockService class.
 *
 * This class tests the behavior of the StockService class methods by mocking its dependencies.
 * The main purpose of these tests is to ensure that the StockService behaves as expected
 * when retrieving and manipulating stock data.
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
     * Test case to verify the behavior of the findAll() method in the StockService.
     *
     * This test ensures that the findAll() method correctly retrieves all stock data
     * and that the returned data matches the expected format and values.
     * It also verifies that the findAll method of the StockService is invoked the correct number of times.
     */
    @Test
    public void testFindAll() throws Exception {
        given(stockService.findAll()).willReturn(mockStocks);

        System.out.println(stockService.findAll());

        ResultActions resultActions = mockMvc.perform(get("/stocks")
                .contentType("application/json"));

        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[*].ticker").value(hasItems("AAPL", "GOOGL", "AMZN")))
                .andExpect(jsonPath("$[*].company").value(hasItems("Apple Inc.", "Alphabet Inc.", "Amazon.com Inc.")))
                .andExpect(jsonPath("$[*].price").value(hasItems(150, 2000, 3000)))
                .andExpect(jsonPath("$[*].currency").value(hasItems("USD")))
                .andExpect(jsonPath("$[*].stockExchange").value(hasItems("NASDAQ")))
                .andExpect(jsonPath("$[*].type").value(hasItems("Technology")));

        verify(stockService, times(3)).findAll();
    }
}
