package com.recruitment.task.infrastructure.transaction;

import com.recruitment.task.domain.transaction.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

interface TransactionDbRepository extends JpaRepository<Transaction, Long> {

    Page<Transaction> findAll(@Nullable Specification<Transaction> specification, Pageable pageable);
}
