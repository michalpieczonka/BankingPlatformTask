package com.recruitment.task.infrastructure.transaction;

import com.recruitment.task.domain.transaction.Transaction;
import com.recruitment.task.domain.transaction.TransactionRepository;
import com.recruitment.task.domain.transaction.TransactionsPage;
import com.recruitment.task.domain.transaction.command.GetTransactionsCommand;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
class TransactionRepositoryImpl implements TransactionRepository {
    private final TransactionDbRepository transactionDbRepository;

    @Override
    public TransactionsPage getTransactions(GetTransactionsCommand getTransactionsCommand) {
        Pageable pageable = PageRequest.of(getTransactionsCommand.getPage(),
                getTransactionsCommand.getSize());

        Specification<Transaction> specification = new TransactionSpecification(getTransactionsCommand);
        Page<Transaction> transactionsPage = transactionDbRepository.findAll(specification, pageable);
        return TransactionsPage.builder()
                .totalElements(transactionsPage.getTotalElements())
                .content(transactionsPage.getContent())
                .build();
    }
}
