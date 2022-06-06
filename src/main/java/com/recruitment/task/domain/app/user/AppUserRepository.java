package com.recruitment.task.domain.app.user;

import java.util.Optional;

public interface AppUserRepository {
    Optional<AppUser> findUserById(long userId);
}
