package com.springapi.tests.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.springapi.api.controllers.StockController;
import com.springapi.api.models.Stocks;
import com.springapi.api.services.StockService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class StockControllerTest {

    @Mock
    private StockService stockService;

    @InjectMocks
    private StockController stockController;

    @Test
    public void shouldReturnAllStocks() {
        // Arrange
        Stocks stock = new Stocks("AAPL", "Apple Inc.", 150, "USD", "NASDAQ", "Technology");
        when(stockService.findAll()).thenReturn(Collections.singletonList(stock));

        // Act
        List<Stocks> stocks = stockController.getAllStocks();
        System.out.println("Stocks: " + stocks);

        // Assert
        assertEquals(1, stocks.size());
        assertEquals("AAPL", stocks.get(0).getTicker());

        // Verify interactions
        verify(stockService, times(1)).findAll();
    }

    //todo Write similar tests for other controller methods
}

