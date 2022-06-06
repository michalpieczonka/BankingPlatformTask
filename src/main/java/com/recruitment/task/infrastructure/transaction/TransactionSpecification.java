package com.recruitment.task.infrastructure.transaction;

import com.recruitment.task.domain.transaction.Transaction;
import com.recruitment.task.domain.transaction.command.GetTransactionsCommand;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
class TransactionSpecification implements Specification<Transaction> {
    private final GetTransactionsCommand getTransactionsCommand;

    @Override
    public Predicate toPredicate(Root<Transaction> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        addPredicate(predicates, transactionDateToPredicate(root, cb, getTransactionsCommand));
        addPredicate(predicates, transactionDateFromPredicate(root, cb, getTransactionsCommand));
        addPredicate(predicates, currencyTypePredicate(root, cb, getTransactionsCommand));
        addPredicate(predicates, transactionTypePredicate(root, cb, getTransactionsCommand));
        return cb.and(predicates.toArray(new Predicate[0]));
    }

    private void addPredicate(List<Predicate> predicates, Optional<Predicate> predicate){
        predicate.ifPresent(predicates::add);
    }

    private Optional<Predicate> transactionDateToPredicate(Root<Transaction> root, CriteriaBuilder cb, GetTransactionsCommand command){
        return command.getTransactionDateTo().map(transactionDateTo -> cb.lessThanOrEqualTo(root.get(Transaction.TRANSACTION_DATE_COLUMN_NAME), transactionDateTo.atStartOfDay()));
    }

    private Optional<Predicate> transactionDateFromPredicate(Root<Transaction> root, CriteriaBuilder cb, GetTransactionsCommand command){
        return command.getTransactionDateFrom().map(transactionDateFrom -> cb.greaterThanOrEqualTo(root.get(Transaction.TRANSACTION_DATE_COLUMN_NAME), transactionDateFrom.atStartOfDay()));
    }

    private Optional<Predicate> currencyTypePredicate(Root<Transaction> root, CriteriaBuilder cb, GetTransactionsCommand command){
        return command.getCurrencyType().map(currencyType -> cb.equal(root.get(Transaction.CURRENCY_TYPE_COLUMN_NAME), currencyType));
    }

    private Optional<Predicate> transactionTypePredicate(Root<Transaction> root, CriteriaBuilder cb, GetTransactionsCommand command){
        return command.getTransactionType().map(transactionType -> cb.equal(root.get(Transaction.TRANSACTION_TYPE_COLUMN_NAME), transactionType));
    }
}
