package com.springapi.api.models;

import com.springapi.api.common.utils.Utils;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a stock entity listed on a stock market.
 */
@Entity
@Data
@NoArgsConstructor
public class Stocks {
    @Id
    @Setter(AccessLevel.NONE)
    private String ticker;
    private String company;
    private Integer price;
    private String currency;
    private String stockExchange;
    private String type;

    /**
     * Constructor to create a new Stocks instance with the specified parameters.
     *
     * @param ticker        the unique identifier for the stock
     * @param company       the name of the company associated with the stock
     * @param price         the current price of the stock
     * @param currency      the currency in which the stock price is quoted
     * @param stockExchange the stock exchange where the stock is listed
     * @param type          the type or category of the stock
     */
    public Stocks(String ticker, String company, Integer price, String currency, String stockExchange, String type) {
        validateFields(ticker, company, price, currency, stockExchange, type);
        this.ticker = ticker;
        this.company = company;
        this.price = price;
        this.currency = currency;
        this.stockExchange = stockExchange;
        this.type = type;
    }

    public Stocks(String s) {
    }

    /**
     * Validates the fields of the Stocks instance.
    */
    private void validateFields(String ticker, String company, Integer price, String currency, String stockExchange, String type) {
        Utils.validateNotEmpty(ticker, "Ticker");
        Utils.validateNotEmpty(company, "Company name");
        Utils.validateNotEmpty(currency, "Currency");
        Utils.validateNotEmpty(stockExchange, "Stock exchange");
        Utils.validateNotEmpty(type, "Type");

        if (price == null || price <= 0) {
            throw new IllegalArgumentException("Price must be a positive integer");
        }
    }
}
