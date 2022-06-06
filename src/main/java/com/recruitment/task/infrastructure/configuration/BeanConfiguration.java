package com.recruitment.task.infrastructure.configuration;

import com.recruitment.task.TaskApplication;
import com.recruitment.task.domain.account.AccountOperationFactory;
import com.recruitment.task.domain.account.AccountRepository;
import com.recruitment.task.domain.account.AccountService;
import com.recruitment.task.domain.account.AccountServiceImpl;
import com.recruitment.task.domain.app.user.AppUserMapper;
import com.recruitment.task.domain.app.user.AppUserRepository;
import com.recruitment.task.domain.app.user.AppUserService;
import com.recruitment.task.domain.app.user.AppUserServiceImpl;
import com.recruitment.task.domain.transaction.CurrencyCalculator;
import com.recruitment.task.domain.transaction.TransactionMapper;
import com.recruitment.task.domain.transaction.TransactionRepository;
import com.recruitment.task.domain.transaction.TransactionService;
import com.recruitment.task.domain.transaction.TransactionServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = TaskApplication.class)
class BeanConfiguration {
    @Bean
    AccountService accountService(AccountRepository accountRepository, CurrencyCalculator currencyCalculator){
        return new AccountServiceImpl(accountRepository, new AccountOperationFactory(currencyCalculator));
    }

    @Bean
    TransactionService transactionService(TransactionRepository transactionRepository, TransactionMapper transactionMapper){
        return new TransactionServiceImpl(transactionRepository, transactionMapper);
    }

    @Bean
    AppUserService appUserService(AppUserRepository appUserRepository, AppUserMapper appUserMapper){
        return new AppUserServiceImpl(appUserRepository, appUserMapper);
    }
}
