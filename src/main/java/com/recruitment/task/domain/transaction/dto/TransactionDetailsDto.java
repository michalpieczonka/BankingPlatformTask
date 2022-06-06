package com.recruitment.task.domain.transaction.dto;

import com.recruitment.task.domain.transaction.CurrencyType;
import com.recruitment.task.domain.transaction.TransactionType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionDetailsDto {
    private long transactionId;
    private LocalDate transactionDate;
    private String sourceAccountNumber;
    private String targetAccountNumber;
    private CurrencyType currencyType;
    private TransactionType transactionType;
    private BigDecimal transactionAmount;
}
