package com.recruitment.task.domain.account.dto;

import com.recruitment.task.domain.transaction.CurrencyType;
import com.recruitment.task.domain.transaction.dto.TransactionDetailsDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountDto {
    private String accountNumber;
    private CurrencyType currencyType;
    private BigDecimal balance;
    private List<TransactionDetailsDto> transactions;
}
