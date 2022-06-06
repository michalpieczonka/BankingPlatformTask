package com.recruitment.task.infrastructure.app.user;

import com.recruitment.task.domain.app.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserDbRepository extends JpaRepository<AppUser, Long> {
}
