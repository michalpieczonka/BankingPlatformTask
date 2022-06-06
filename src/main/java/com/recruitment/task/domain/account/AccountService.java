package com.recruitment.task.domain.account;

import com.recruitment.task.domain.account.command.AddBalanceCommand;
import com.recruitment.task.domain.account.command.ExchangeFundsCommand;
import com.recruitment.task.domain.account.command.TransferFundsCommand;
import com.recruitment.task.domain.account.command.WithdrawFundsCommand;

public interface AccountService {
    void withdrawFunds(WithdrawFundsCommand withdrawFundsCommand);
    void addBalance(AddBalanceCommand addBalanceCommand);
    void transferFunds(TransferFundsCommand transferFundsCommand);
    void exchangeFunds(ExchangeFundsCommand exchangeFundsCommand);
}
