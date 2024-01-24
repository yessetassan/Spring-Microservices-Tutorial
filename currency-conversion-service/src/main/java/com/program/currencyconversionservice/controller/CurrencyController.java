package com.program.currencyconversionservice.controller;


import com.program.currencyconversionservice.model.CurrencyConversion;
import com.program.currencyconversionservice.repo.FeignClientCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@RestController
public class CurrencyController {

    @Autowired
    private FeignClientCall feignClientCall;



    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public ResponseEntity<CurrencyConversion> get(@PathVariable String from,
                                                  @PathVariable String to,
                                                  @PathVariable BigDecimal quantity
                                  ) {

        CurrencyConversion currencyConversion = feignClientCall.receive(
                from,
                to
        );

        if (currencyConversion != null) {
            currencyConversion.setCost(quantity.multiply(currencyConversion.getConversionMultiple()));
            return ResponseEntity.ok().body(currencyConversion);
        }

        return ResponseEntity.badRequest().build();
    }


}
