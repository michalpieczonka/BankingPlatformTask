package com.recruitment.task.domain.account;

import java.util.Optional;

public interface AccountRepository {
    Optional<Account> getAccountById(long accountId);
}
