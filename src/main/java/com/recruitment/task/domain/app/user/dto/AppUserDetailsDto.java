package com.recruitment.task.domain.app.user.dto;

import com.recruitment.task.domain.account.dto.AccountDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AppUserDetailsDto {
    private long userId;
    private List<AccountDto> accountDetails;
}
