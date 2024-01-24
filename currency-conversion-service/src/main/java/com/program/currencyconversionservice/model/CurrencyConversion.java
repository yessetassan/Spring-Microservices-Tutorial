package com.program.currencyconversionservice.model;


import lombok.*;

import java.math.BigDecimal;

@Data
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrencyConversion {
    private Integer id;
    private String from;
    private String to;
    private BigDecimal conversionMultiple;
    private String env;
    private BigDecimal cost;
}
