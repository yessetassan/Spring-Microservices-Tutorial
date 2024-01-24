package com.program.currencyexchangeservice.controller;

import com.program.currencyexchangeservice.model.CurrencyExchange;
import com.program.currencyexchangeservice.services.CurrencyExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;
    @Autowired
    private CurrencyExchangeService currencyExchangeService;

    @GetMapping("/currency-exchange-server/from/{from}/to/{to}")
    public ResponseEntity<CurrencyExchange> retrieve(
            @PathVariable("from") String from,
            @PathVariable("to") String to
    )  throws Exception{

        Optional<CurrencyExchange> currencyExchange = currencyExchangeService.findByFromAndTo(
                from,
                to
        );
        return currencyExchange.map(exchange ->
                ResponseEntity.ok().body(exchange)).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange receive(
            @PathVariable("from") String from,
            @PathVariable("to") String to
    )  throws Exception{

        Optional<CurrencyExchange> currencyExchange = currencyExchangeService.findByFromAndTo(
                from,
                to
        );
        currencyExchange.get().setEnv(environment.getProperty("local.server.port"));

        return currencyExchange.orElse(null);

    }

    @PostMapping("/currency-exchange/from/{from}/to/{to}")
    public ResponseEntity<CurrencyExchange> post(
            @PathVariable("from") String from,
            @PathVariable("to") String to
    )  throws Exception{
        CurrencyExchange currencyExchange = CurrencyExchange.builder()
                .from(from)
                .to(to)
                .conversionMultiple(BigDecimal.TEN)
                .build();

        if (currencyExchangeService.findByFromAndTo(from,to).isEmpty()) {
            currencyExchangeService.save(currencyExchange);
            return ResponseEntity.ok().body(currencyExchange);
        }

        return ResponseEntity.badRequest().build();
    }

}
