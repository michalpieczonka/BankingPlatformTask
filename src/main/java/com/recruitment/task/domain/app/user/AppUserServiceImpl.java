package com.recruitment.task.domain.app.user;

import com.recruitment.task.domain.app.user.dto.AppUserDetailsDto;
import com.recruitment.task.domain.app.user.exception.UserNotFoundException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AppUserServiceImpl implements AppUserService{
    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;

    @Override
    public AppUserDetailsDto getAppUserDetails(long userId) {
        AppUser appUser = appUserRepository.findUserById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        appUser.getUserAccounts().forEach(acc -> acc.getTransactions().forEach(tr -> System.out.println(tr.getId())));
        return appUserMapper.toAppUserDetailsDto(appUser);
    }
}
