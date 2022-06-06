package com.recruitment.task.infrastructure.app.user;

import com.recruitment.task.domain.app.user.AppUser;
import com.recruitment.task.domain.app.user.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
class AppUserRepositoryImpl implements AppUserRepository {
    private final AppUserDbRepository appUserDbRepository;

    @Override
    public Optional<AppUser> findUserById(long userId) {
        return appUserDbRepository.findById(userId);
    }
}
