package com.example.wealthFund.service;

import com.example.wealthFund.repository.CashTransactionRepository;
import com.example.wealthFund.repository.entity.UserCashTransactionEntity;
import com.example.wealthFund.repository.entity.WalletEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CashTransactionService {

    private final CashTransactionRepository cashTransactionRepository;

    public CashTransactionService(CashTransactionRepository cashTransactionRepository) {
        this.cashTransactionRepository = cashTransactionRepository;
    }

    public WalletEntity addNewPositiveCashTransaction(Float value, WalletEntity walletEntity){

        return addNewCashTransaction(value, walletEntity);
    }

    public WalletEntity addNewNegativeCashTransaction(Float value, WalletEntity walletEntity){

        return addNewCashTransaction(value * -1, walletEntity);
    }

    private WalletEntity addNewCashTransaction(Float value, WalletEntity walletEntity) {

        UserCashTransactionEntity userCashTransactionEntity = UserCashTransactionEntity.builder()
                .value(value)
                .date(LocalDateTime.now())
                .build();

        cashTransactionRepository.save(userCashTransactionEntity);

        List<UserCashTransactionEntity> userCashTransactionEntityList = walletEntity.getUserTransactions();
        userCashTransactionEntityList.add(userCashTransactionEntity);

        walletEntity.setUserTransactions(userCashTransactionEntityList);

        return walletEntity;
    }
}
