package com.recruitment.task.domain.transaction;

import com.recruitment.task.domain.transaction.command.GetTransactionsCommand;

public interface TransactionRepository {
    TransactionsPage getTransactions(GetTransactionsCommand getTransactionsCommand);
}
