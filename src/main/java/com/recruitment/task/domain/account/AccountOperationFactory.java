package com.recruitment.task.domain.account;

import com.recruitment.task.domain.account.command.AddBalanceCommand;
import com.recruitment.task.domain.account.command.ExchangeFundsCommand;
import com.recruitment.task.domain.account.command.TransferFundsCommand;
import com.recruitment.task.domain.account.command.WithdrawFundsCommand;
import com.recruitment.task.domain.account.exception.InconsistentDetailsException;
import com.recruitment.task.domain.account.exception.InvalidOperationAmountException;
import com.recruitment.task.domain.transaction.CurrencyCalculator;
import com.recruitment.task.domain.transaction.CurrencyCalculatorImpl;
import com.recruitment.task.domain.transaction.CurrencyType;
import com.recruitment.task.domain.transaction.Transaction;
import com.recruitment.task.domain.transaction.TransactionType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
public class AccountOperationFactory {
    private static final String INCONSISTENT_CURRENCY_TYPE = "Operation currency not matching account currency";
    private static final String BALANCE_NOT_POSITIVE = "Balance amount has to be positive value";
    private static final String NOT_ENOUGH_FUNDS = "Can not withdraw. The current account balance is less than the amount to be withdrawn";

    private final CurrencyCalculator currencyCalculator;

    public Transaction createDepositTransaction(AddBalanceCommand addBalanceCommand, Account account){
        if (addBalanceCommand.getAmount().compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidOperationAmountException(BALANCE_NOT_POSITIVE);
        }
        validateCurrencyConsistency(account.getCurrencyType(), addBalanceCommand.getCurrencyType());

        Transaction depositTransaction = createTransaction(TransactionType.DEPOSIT, addBalanceCommand.getCurrencyType(),
                null, account, addBalanceCommand.getAmount());

        account.addBalance(addBalanceCommand.getAmount());
        return depositTransaction;
    }

    public Transaction createWithdrawTransaction(WithdrawFundsCommand withdrawFundsCommand, Account account){
        checkWithdrawPossibility(account, withdrawFundsCommand.getWithdrawAmount());
        validateCurrencyConsistency(account.getCurrencyType(), withdrawFundsCommand.getCurrencyType());

        Transaction withdrawTransaction = createTransaction(TransactionType.WITHDRAW, withdrawFundsCommand.getCurrencyType(),
                account, null, withdrawFundsCommand.getWithdrawAmount());

        account.withdrawFunds(withdrawFundsCommand.getWithdrawAmount());
        return withdrawTransaction;
    }

    public Transaction createTransferTransaction(TransferFundsCommand transferFundsCommand, Account sourceAccount, Account targetAccount){
        checkWithdrawPossibility(sourceAccount, transferFundsCommand.getTransferAmount());
        validateCurrencyConsistency(sourceAccount.getCurrencyType(), transferFundsCommand.getCurrencyType());
        validateCurrencyConsistency(targetAccount.getCurrencyType(), transferFundsCommand.getCurrencyType());

        Transaction transferTransaction = createTransaction(TransactionType.TRANSFER, transferFundsCommand.getCurrencyType(),
                sourceAccount, targetAccount, transferFundsCommand.getTransferAmount());

        sourceAccount.withdrawFunds(transferFundsCommand.getTransferAmount());
        targetAccount.addBalance(transferFundsCommand.getTransferAmount());
        return transferTransaction;
    }

    public Transaction exchangeFunds(ExchangeFundsCommand exchangeFundsCommand, Account sourceAccount, Account targetAccount){
        checkWithdrawPossibility(sourceAccount, exchangeFundsCommand.getExchangeAmount());

        BigDecimal targetCurrencyAmount = currencyCalculator.calculate(exchangeFundsCommand.getExchangeAmount(), exchangeFundsCommand.getSourceCurrency(), exchangeFundsCommand.getTargetCurrency());
        Transaction exchangeFundsTransaction = createTransaction(TransactionType.EXCHANGE, exchangeFundsCommand.getTargetCurrency(),
                sourceAccount, targetAccount, targetCurrencyAmount);

        sourceAccount.withdrawFunds(exchangeFundsCommand.getExchangeAmount());
        sourceAccount.addBalance(targetCurrencyAmount);
        return exchangeFundsTransaction;
    }

    private void checkWithdrawPossibility(Account account, BigDecimal withdrawAmount){
        if (account.getBalance().compareTo(withdrawAmount) < 0){
            throw new InvalidOperationAmountException(NOT_ENOUGH_FUNDS);
        }
    }

    private void validateCurrencyConsistency(CurrencyType accountCurrencyType, CurrencyType operationCurrencyType){
        if (accountCurrencyType != operationCurrencyType){
            throw new InconsistentDetailsException(INCONSISTENT_CURRENCY_TYPE);
        }
    }

    private Transaction createTransaction(TransactionType transactionType, CurrencyType currencyType, Account sourceAccount, Account targetAccount, BigDecimal transactionAmount){
        return Transaction.builder()
                .transactionDate(LocalDateTime.now())
                .currencyType(currencyType)
                .sourceAccount(sourceAccount)
                .targetAccount(targetAccount)
                .transactionType(transactionType)
                .transactionAmount(transactionAmount)
                .build();
    }
}
