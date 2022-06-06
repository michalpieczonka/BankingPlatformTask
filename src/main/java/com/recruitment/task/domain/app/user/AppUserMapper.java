package com.recruitment.task.domain.app.user;

import com.recruitment.task.domain.account.Account;
import com.recruitment.task.domain.account.dto.AccountDto;
import com.recruitment.task.domain.app.user.dto.AppUserDetailsDto;
import com.recruitment.task.domain.transaction.Transaction;
import com.recruitment.task.domain.transaction.dto.TransactionDetailsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AppUserMapper {

    @Mapping(target = "userId", source = "appUser.id")
    @Mapping(target = "accountDetails",source = "appUser", qualifiedByName = "toMapAccountDetails")
    AppUserDetailsDto toAppUserDetailsDto(AppUser appUser);

    @Mapping(source = "account.transactions", target = "transactions", qualifiedByName = "toTransactionDetailsDto")
    AccountDto toAccountDto(Account account);

    @Mapping(source = "transaction.id", target ="transactionId")
    @Mapping(source = "transaction.sourceAccount.accountNumber", target = "sourceAccountNumber")
    @Mapping(source = "transaction.targetAccount.accountNumber", target = "targetAccountNumber")
    TransactionDetailsDto mapTransaction(Transaction transaction);

    @Named("toMapAccountDetails")
    default List<AccountDto> mapAccountDetails(AppUser appUser){
        return appUser.getUserAccounts().stream()
                .map(this::toAccountDto)
                .collect(Collectors.toList());
    }

    @Named("toTransactionDetailsDto")
    default List<TransactionDetailsDto> toTransactionDetailsDto(Set<Transaction> transactions){
        return transactions.stream().map(this::mapTransaction).collect(Collectors.toList());
    }

}
