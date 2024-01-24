package com.program.currencyexchangeservice.services;

import com.program.currencyexchangeservice.model.CurrencyExchange;
import com.program.currencyexchangeservice.repo.CurrencyExchangeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CurrencyExchangeService {

    @Autowired
    private CurrencyExchangeRepo currencyExchangeRepo;

    public Optional<CurrencyExchange> findByFromAndTo(String from,
                                                      String to) {
        return currencyExchangeRepo.findByFromAndTo(from,
                to);
    }


    public void save(CurrencyExchange currencyExchange) {
        currencyExchangeRepo.save(currencyExchange);
    }
}
