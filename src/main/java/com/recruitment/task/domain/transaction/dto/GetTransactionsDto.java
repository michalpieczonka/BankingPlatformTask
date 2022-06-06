package com.recruitment.task.domain.transaction.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GetTransactionsDto {
    private long totalElements;
    private List<TransactionDetailsDto> content;
}
