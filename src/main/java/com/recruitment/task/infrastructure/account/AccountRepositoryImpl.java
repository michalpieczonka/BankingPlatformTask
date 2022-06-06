package com.recruitment.task.infrastructure.account;

import com.recruitment.task.domain.account.Account;
import com.recruitment.task.domain.account.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
class AccountRepositoryImpl implements AccountRepository {
    private final AccountDbRepository accountDbRepository;

    @Override
    public Optional<Account> getAccountById(long accountId) {
        return accountDbRepository.findById(accountId);
    }
}
