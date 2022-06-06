package com.recruitment.task.domain.account.command;

import com.recruitment.task.domain.transaction.CurrencyType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ExchangeFundsCommand {
    private final long sourceAccountId;
    private final long targetAccountId;
    private final BigDecimal exchangeAmount;
    private final CurrencyType sourceCurrency;
    private final CurrencyType targetCurrency;
}
