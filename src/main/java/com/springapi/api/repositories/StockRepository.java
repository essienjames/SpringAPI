package com.springapi.api.repositories;

import com.springapi.api.models.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stocks, String> {
    Optional<Stocks> findByTicker(String ticker);
    List<Stocks> findByStockExchange(String stockExchange);
    List<Stocks> findByCurrency(String currency);
    List<Stocks> findByType(String type);
}
