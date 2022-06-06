package com.recruitment.task.domain.transaction.command;

import com.recruitment.task.domain.transaction.CurrencyType;
import com.recruitment.task.domain.transaction.TransactionType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Optional;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetTransactionsCommand {
    private final int page;
    private final int size;
    private final LocalDate transactionDateFrom;
    private final LocalDate transactionDateTo;
    private final CurrencyType currencyType;
    private final TransactionType transactionType;

    public Optional<LocalDate> getTransactionDateFrom(){
        return Optional.ofNullable(transactionDateFrom);
    }

    public Optional<LocalDate> getTransactionDateTo(){
        return Optional.ofNullable(transactionDateTo);
    }

    public Optional<CurrencyType> getCurrencyType(){
        return Optional.ofNullable(currencyType);
    }

    public Optional<TransactionType> getTransactionType(){
        return Optional.ofNullable(transactionType);
    }
}
