package com.program.currencyconversionservice.repo;


import com.program.currencyconversionservice.model.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "currency-exchange")
public interface FeignClientCall {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    CurrencyConversion receive(@PathVariable String from,
                                      @PathVariable String to);
}
