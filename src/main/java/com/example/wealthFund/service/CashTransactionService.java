package com.example.wealthFund.service;

import com.example.wealthFund.repository.CashTransactionRepository;
import com.example.wealthFund.repository.entity.UserCashTransaction;
import com.example.wealthFund.repository.entity.Wallet;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CashTransactionService {

    private final CashTransactionRepository cashTransactionRepository;

    public CashTransactionService(CashTransactionRepository cashTransactionRepository) {
        this.cashTransactionRepository = cashTransactionRepository;
    }

    public Wallet addNewPositiveCashTransaction(Float value, Wallet wallet){

        return addNewCashTransaction(value, wallet);
    }

    public Wallet addNewNegativeCashTransaction(Float value, Wallet wallet){

        return addNewCashTransaction(value * -1, wallet);
    }

    private Wallet addNewCashTransaction(Float value, Wallet wallet) {

        UserCashTransaction userCashTransaction = new UserCashTransaction();
        userCashTransaction.setValue(value);
        userCashTransaction.setDate(LocalDateTime.now());
        cashTransactionRepository.save(userCashTransaction);

        List<UserCashTransaction> userCashTransactionList = wallet.getUserTransactions();
        userCashTransactionList.add(userCashTransaction);
        wallet.setUserTransactions(userCashTransactionList);

        return wallet;
    }
}
