package com.recruitment.task.domain.transaction;

import com.recruitment.task.domain.transaction.command.GetTransactionsCommand;
import com.recruitment.task.domain.transaction.dto.GetTransactionsDto;

public interface TransactionService {
    GetTransactionsDto getTransactions(GetTransactionsCommand getTransactionsCommand);
}
