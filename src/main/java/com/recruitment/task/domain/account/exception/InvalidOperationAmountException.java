package com.recruitment.task.domain.account.exception;

import com.recruitment.task.domain.exception.BusinessException;

public class InvalidOperationAmountException extends BusinessException {
    public InvalidOperationAmountException(String message) {
        super(message);
    }
}
