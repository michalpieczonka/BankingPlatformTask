package com.recruitment.task.domain.transaction;

import com.recruitment.task.domain.transaction.command.GetTransactionsCommand;
import com.recruitment.task.domain.transaction.dto.GetTransactionsDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService{
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public GetTransactionsDto getTransactions(GetTransactionsCommand getTransactionsCommand) {
        TransactionsPage transactionsPage = transactionRepository.getTransactions(getTransactionsCommand);
        return transactionMapper.mapTransactionsPage(transactionsPage);
    }
}
