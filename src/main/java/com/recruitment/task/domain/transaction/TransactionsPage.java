package com.recruitment.task.domain.transaction;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionsPage {
    private final long totalElements;
    private final List<Transaction> content;
}
