package com.springapi.api.models;

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
}
