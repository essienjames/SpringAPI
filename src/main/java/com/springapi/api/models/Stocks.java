package com.springapi.api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Stocks {
    @Id
    private String ticker;
    private String company;
    private Integer price;
    private String currency;
    private String stockExchange;
    private String type;
}
