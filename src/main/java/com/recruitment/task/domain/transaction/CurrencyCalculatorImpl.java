package com.recruitment.task.domain.transaction;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
public class CurrencyCalculatorImpl implements CurrencyCalculator {

    private final List<ExchangeRate> exchangeRates;

    @Override
    public BigDecimal calculate(BigDecimal amount, CurrencyType sourceCurrency, CurrencyType targetCurrency) {
        exchangeRates.forEach(s ->System.out.println(s.getSourceCurrency()) );
        BigDecimal currentExchangeRate = exchangeRates.stream()
                .filter(exchangeRate -> exchangeRate.getSourceCurrency() == sourceCurrency && exchangeRate.getTargetCurrency() == targetCurrency)
                .map(ExchangeRate::getAmount)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Currency not found"));

        return calculateExchange(amount, currentExchangeRate);
    }

    private BigDecimal calculateExchange(BigDecimal amount, BigDecimal exchangeRate){
        return amount.multiply(exchangeRate);
    }

}
