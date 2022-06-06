package com.recruitment.task.infrastructure.account;

import com.recruitment.task.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

interface AccountDbRepository extends JpaRepository<Account, Long> {
}
