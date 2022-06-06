package com.recruitment.task.domain.transaction;

import com.recruitment.task.domain.account.Account;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "transactions")
public class Transaction {

    public static final String TRANSACTION_DATE_COLUMN_NAME = "transactionDate";
    public static final String CURRENCY_TYPE_COLUMN_NAME = "currencyType";
    public static final String TRANSACTION_TYPE_COLUMN_NAME = "transactionType";

    @Id
    @SequenceGenerator(
            name = "accounts",
            sequenceName = "accounts_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "accounts_sequence"
    )
    private Long id;

    private LocalDateTime transactionDate;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;

    @Setter
    @ManyToOne (fetch = FetchType.LAZY)
    private Account sourceAccount;

    @Setter
    @ManyToOne (fetch = FetchType.LAZY)
    private Account targetAccount;

    private BigDecimal transactionAmount;
}
