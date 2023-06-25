package com.example.wealthFund.service;

import com.example.wealthFund.exception.InsufficientFundsException;
import com.example.wealthFund.repository.UserRepository;
import com.example.wealthFund.repository.WalletRepository;
import com.example.wealthFund.repository.entity.CashEntity;
import com.example.wealthFund.repository.entity.UserEntity;
import com.example.wealthFund.repository.entity.WalletEntity;
import org.springframework.stereotype.Service;

@Service
public class CashService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final TextValidator textValidator;
    private final UserService userService;
    private final WalletService walletService;
    private final CashTransactionService cashTransactionService;

    public CashService(UserRepository userRepository, WalletRepository walletRepository,
                       TextValidator textValidator, UserService userService,
                       WalletService walletservice, CashTransactionService cashTransactionService) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
        this.textValidator = textValidator;
        this.userService = userService;
        this.walletService = walletservice;
        this.cashTransactionService = cashTransactionService;
    }

    public boolean depositCashIntoTheWallet(String userName, String walletName, float valueOfDeposit) {

        textValidator.checkNumberValidity(valueOfDeposit);
        UserEntity userEntity = userService.getUserByName(userName);
        WalletEntity walletEntity = walletService.getWalletByName(userEntity, walletName);

        addCashToWallet(walletEntity, valueOfDeposit);
        walletEntity = cashTransactionService.addNewPositiveCashTransaction(valueOfDeposit, walletEntity);

        walletRepository.save(walletEntity);
        userRepository.save(userEntity);
        return true;
    }

    public boolean withdrawCashFromTheWallet(String userName, String walletName, float valueOfWithdraw) {

        textValidator.checkNumberValidity(valueOfWithdraw);
        UserEntity userEntity = userService.getUserByName(userName);
        WalletEntity walletEntity = walletService.getWalletByName(userEntity, walletName);

        withdrawCashFromWallet(walletEntity, valueOfWithdraw);
        walletEntity = cashTransactionService.addNewNegativeCashTransaction(valueOfWithdraw, walletEntity);

        walletRepository.save(walletEntity);
        userRepository.save(userEntity);
        return true;
    }

    private void addCashToWallet(WalletEntity walletEntity, float valueOfDeposit) {

        CashEntity cashEntity = getOrCreateCash(walletEntity);
        walletEntity.setCashEntity(cashEntity);
        cashEntity.setValue(cashEntity.getValue() + valueOfDeposit);
    }

    private void withdrawCashFromWallet(WalletEntity walletEntity, float valueOfWithdraw) {

        CashEntity cashEntity = getOrCreateCash(walletEntity);
        walletEntity.setCashEntity(cashEntity);
        float previousValueOfCash = cashEntity.getValue();
        if (previousValueOfCash < valueOfWithdraw) {
            throw new InsufficientFundsException(previousValueOfCash, valueOfWithdraw, walletEntity.getCurrency());
        }
        cashEntity.setValue(previousValueOfCash - valueOfWithdraw);
    }

    private CashEntity getOrCreateCash(WalletEntity walletEntity) {

        CashEntity cashEntity = walletEntity.getCashEntity();
        if (cashEntity == null) {
            cashEntity = new CashEntity();
        }
        return cashEntity;
    }
}
