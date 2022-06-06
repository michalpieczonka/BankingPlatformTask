package com.recruitment.task.domain.transaction;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class ExchangeRate {
    private CurrencyType sourceCurrency;
    private CurrencyType targetCurrency;
    private BigDecimal amount;
}
