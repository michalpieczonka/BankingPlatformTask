package com.recruitment.task.application;

import com.recruitment.task.domain.account.AccountService;
import com.recruitment.task.domain.account.command.AddBalanceCommand;
import com.recruitment.task.domain.account.command.ExchangeFundsCommand;
import com.recruitment.task.domain.account.command.TransferFundsCommand;
import com.recruitment.task.domain.account.command.WithdrawFundsCommand;
import com.recruitment.task.domain.app.user.AppUserService;
import com.recruitment.task.domain.app.user.dto.AppUserDetailsDto;
import com.recruitment.task.domain.transaction.TransactionService;
import com.recruitment.task.domain.transaction.command.GetTransactionsCommand;
import com.recruitment.task.domain.transaction.dto.GetTransactionsDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TestController {
    private final AccountService accountService;
    private final TransactionService transactionService;
    private final AppUserService appUserService;

    @PostMapping("/addBalance")
    void addBalance(@RequestBody AddBalanceCommand addBalanceCommand){
        accountService.addBalance(addBalanceCommand);
    }

    @PostMapping("/withDrawFunds")
    void withDrawFunds(@RequestBody WithdrawFundsCommand withdrawFundsCommand){
        accountService.withdrawFunds(withdrawFundsCommand);
    }

    @PostMapping("/transferFunds")
    void withDrawFunds(@RequestBody TransferFundsCommand withdrawFundsCommand){
        accountService.transferFunds(withdrawFundsCommand);
    }

    @GetMapping("/transactions")
    GetTransactionsDto test(@RequestBody GetTransactionsCommand getTransactionsCommand){
        return transactionService.getTransactions(getTransactionsCommand);
    }
    @PostMapping("/exchange")
    void exhnage(@RequestBody ExchangeFundsCommand exchangeFundsCommand){
        accountService.exchangeFunds(exchangeFundsCommand);
    }
    @GetMapping("/users/{id}")
    AppUserDetailsDto getTest(@PathVariable("id") long id){
        return appUserService.getAppUserDetails(id);
    }
}
