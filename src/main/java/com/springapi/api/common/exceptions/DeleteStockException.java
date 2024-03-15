package com.springapi.api.common.exceptions;

import com.springapi.api.models.Stocks;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class DeleteStockException {
    private String message;
    private Stocks deletedStock;

    public DeleteStockException(String message, Stocks deletedStock) {
        this.message = message;
        this.deletedStock = deletedStock;
    }
}
