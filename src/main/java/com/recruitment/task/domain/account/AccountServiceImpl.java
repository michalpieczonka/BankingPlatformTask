package com.recruitment.task.domain.account;

import com.recruitment.task.domain.account.command.AddBalanceCommand;
import com.recruitment.task.domain.account.command.ExchangeFundsCommand;
import com.recruitment.task.domain.account.command.TransferFundsCommand;
import com.recruitment.task.domain.account.command.WithdrawFundsCommand;
import com.recruitment.task.domain.account.exception.AccountNotFoundException;
import com.recruitment.task.domain.transaction.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;
    private final AccountOperationFactory accountOperationFactory;


    @Transactional
    @Override
    public void withdrawFunds(WithdrawFundsCommand withdrawFundsCommand) {
        Account account = accountRepository.getAccountById(withdrawFundsCommand.getSourceAccountId())
                .orElseThrow(() -> new AccountNotFoundException(withdrawFundsCommand.getSourceAccountId()));

        Transaction withdrawalTransaction = accountOperationFactory.createWithdrawTransaction(withdrawFundsCommand, account);
        account.addTransaction(withdrawalTransaction);
        withdrawalTransaction.setSourceAccount(account);

    }

    @Transactional
    @Override
    public void addBalance(AddBalanceCommand addBalanceCommand) {
        Account account = accountRepository.getAccountById(addBalanceCommand.getTargetAccountId())
                .orElseThrow(() -> new AccountNotFoundException(addBalanceCommand.getTargetAccountId()));

        Transaction depositTransaction = accountOperationFactory.createDepositTransaction(addBalanceCommand, account);
        account.addTransaction(depositTransaction);
        depositTransaction.setSourceAccount(account);
    }

    @Transactional
    @Override
    public void transferFunds(TransferFundsCommand transferFundsCommand) {
        Account sourceAccount = accountRepository.getAccountById(transferFundsCommand.getSourceAccountId())
                .orElseThrow(() -> new AccountNotFoundException(transferFundsCommand.getSourceAccountId()));

        Account targetAccount = accountRepository.getAccountById(transferFundsCommand.getTargetAccountId())
                .orElseThrow(() -> new AccountNotFoundException(transferFundsCommand.getTargetAccountId()));

        Transaction transferTransaction = accountOperationFactory.createTransferTransaction(transferFundsCommand, sourceAccount, targetAccount);
        sourceAccount.addTransaction(transferTransaction);
        targetAccount.addTransaction(transferTransaction);
        transferTransaction.setSourceAccount(sourceAccount);
        transferTransaction.setTargetAccount(targetAccount);
    }

    @Transactional
    @Override
    public void exchangeFunds(ExchangeFundsCommand exchangeFundsCommand) {
        Account sourceAccount = accountRepository.getAccountById(exchangeFundsCommand.getSourceAccountId())
                .orElseThrow(() -> new AccountNotFoundException(exchangeFundsCommand.getSourceAccountId()));

        Account targetAccount = accountRepository.getAccountById(exchangeFundsCommand.getTargetAccountId())
                .orElseThrow(() -> new AccountNotFoundException(exchangeFundsCommand.getTargetAccountId()));

        Transaction exchangeTransaction = accountOperationFactory.exchangeFunds(exchangeFundsCommand, sourceAccount, targetAccount);
        sourceAccount.addTransaction(exchangeTransaction);
        targetAccount.addTransaction(exchangeTransaction);
        exchangeTransaction.setSourceAccount(sourceAccount);
        exchangeTransaction.setTargetAccount(targetAccount);
    }

}
