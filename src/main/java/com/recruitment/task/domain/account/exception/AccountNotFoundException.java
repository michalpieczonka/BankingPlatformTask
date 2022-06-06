package com.recruitment.task.domain.account.exception;

import com.recruitment.task.domain.exception.NotFoundException;

public class AccountNotFoundException extends NotFoundException {
    public AccountNotFoundException(long accountId){
        super(String.format("Account with id = %s not found", accountId));
    }

}
