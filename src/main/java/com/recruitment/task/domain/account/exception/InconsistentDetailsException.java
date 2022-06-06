package com.recruitment.task.domain.account.exception;

import com.recruitment.task.domain.exception.BusinessException;

public class InconsistentDetailsException extends BusinessException {
    public InconsistentDetailsException(String message) {
        super(message);
    }
}
