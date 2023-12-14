package com.springapi.api.models;

import lombok.Data;

@Data
public class Stock {
    private String ticker;
    private String companyName;
    private String price;
    private String currency;
    private String stockExchange;
    private String type;

    public Stock(String ticker, String companyName, String price, String currency, String stockExchange, String type) {
        this.ticker = ticker;
        this.companyName = companyName;
        this.price = price;
        this.currency = currency;
        this.stockExchange = stockExchange;
        this.type = type;
    }
}
