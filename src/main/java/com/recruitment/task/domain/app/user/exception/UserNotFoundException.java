package com.recruitment.task.domain.app.user.exception;

import com.recruitment.task.domain.exception.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(long appUserId) {
        super(String.format("Application user with id = %s not found", appUserId));
    }
}
