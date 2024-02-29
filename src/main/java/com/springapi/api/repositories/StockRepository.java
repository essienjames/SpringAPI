package com.springapi.api.repositories;

import com.springapi.api.models.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stocks, String> {
    Optional<Stocks> findByTicker(String ticker);
}
