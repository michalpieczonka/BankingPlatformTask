package com.recruitment.task.domain.account;

import com.recruitment.task.domain.app.user.AppUser;
import com.recruitment.task.domain.transaction.CurrencyType;
import com.recruitment.task.domain.transaction.Transaction;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "accounts")
public class Account {
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

    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;

    @Column(unique = true)
    private String accountNumber;

    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "app_user_id", updatable = false)
    private AppUser accountOwner;

    @OneToMany(mappedBy = "sourceAccount", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    Set<Transaction> transactions = new HashSet<>();

    public void addBalance(BigDecimal amount){
        balance = balance.add(amount);
    }

    public void withdrawFunds(BigDecimal amount){
        balance = balance.subtract(amount);
    }

   public void addTransaction(Transaction transaction){
       transactions.add(transaction);
   }

}
