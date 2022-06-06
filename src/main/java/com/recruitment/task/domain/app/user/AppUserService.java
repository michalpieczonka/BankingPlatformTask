package com.recruitment.task.domain.app.user;

import com.recruitment.task.domain.app.user.dto.AppUserDetailsDto;

public interface AppUserService {
    AppUserDetailsDto getAppUserDetails(long userId);
}
