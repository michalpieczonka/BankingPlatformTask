package com.recruitment.task.domain.transaction;

import com.recruitment.task.domain.transaction.dto.GetTransactionsDto;
import com.recruitment.task.domain.transaction.dto.TransactionDetailsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    GetTransactionsDto mapTransactionsPage(TransactionsPage transactionsPage);

    @Mapping(source = "transaction.id", target ="transactionId")
    @Mapping(source = "transaction.sourceAccount.accountNumber", target = "sourceAccountNumber")
    @Mapping(source = "transaction.targetAccount.accountNumber", target = "targetAccountNumber")
    TransactionDetailsDto toTransactionDto(Transaction transaction);
}
