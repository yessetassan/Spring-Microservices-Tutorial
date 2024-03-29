package com.program.currencyexchangeservice.repo;

import com.program.currencyexchangeservice.model.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyExchangeRepo
        extends JpaRepository<CurrencyExchange, Integer> {

    Optional<CurrencyExchange> findByFromAndTo(String from, String to);
}
