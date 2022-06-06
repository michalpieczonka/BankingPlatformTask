package com.recruitment.task.domain.transaction;

import java.math.BigDecimal;

public interface CurrencyCalculator {
    BigDecimal calculate(BigDecimal amount, CurrencyType sourceCurrency, CurrencyType targetCurrency);
}
