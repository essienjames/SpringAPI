package com.springapi.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "stocks")
public class Stocks {

    @Id
    @Column(name = "ticker")
    private String ticker;

    @Column(name = "company")
    private String company;

    @Column(name = "price")
    private Integer price;

    @Column(name = "currency")
    private String currency;

    @Column(name = "stock_exchange")
    private String stockExchange;

    @Column(name = "type")
    private String type;
}
