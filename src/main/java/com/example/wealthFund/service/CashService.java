package com.example.wealthFund.service;

import com.example.wealthFund.exception.InsufficientFundsException;
import com.example.wealthFund.repository.UserRepository;
import com.example.wealthFund.repository.WalletRepository;
import com.example.wealthFund.repository.entity.Cash;
import com.example.wealthFund.repository.entity.User;
import com.example.wealthFund.repository.entity.Wallet;
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
        User user = userService.getUserByName(userName);
        Wallet wallet = walletService.getWalletByName(user, walletName);

        addCashToWallet(wallet, valueOfDeposit);
        wallet = cashTransactionService.addNewPositiveCashTransaction(valueOfDeposit, wallet);

        walletRepository.save(wallet);
        userRepository.save(user);
        return true;
    }

    public boolean withdrawCashFromTheWallet(String userName, String walletName, float valueOfWithdraw) {

        textValidator.checkNumberValidity(valueOfWithdraw);
        User user = userService.getUserByName(userName);
        Wallet wallet = walletService.getWalletByName(user, walletName);

        withdrawCashFromWallet(wallet, valueOfWithdraw);
        wallet = cashTransactionService.addNewNegativeCashTransaction(valueOfWithdraw, wallet);

        walletRepository.save(wallet);
        userRepository.save(user);
        return true;
    }

    private void addCashToWallet(Wallet wallet, float valueOfDeposit) {

        Cash cash = getOrCreateCash(wallet);
        cash.setValue(cash.getValue() + valueOfDeposit);
    }

    private void withdrawCashFromWallet(Wallet wallet, float valueOfWithdraw) {

        Cash cash = getOrCreateCash(wallet);
        float previousValueOfCash = cash.getValue();
        if (previousValueOfCash < valueOfWithdraw) {
            throw new InsufficientFundsException(previousValueOfCash, valueOfWithdraw, wallet.getCurrency());
        }
        cash.setValue(previousValueOfCash - valueOfWithdraw);
    }

    private Cash getOrCreateCash(Wallet wallet) {

        Cash cash = wallet.getCash();
        if (cash == null) {
            cash = new Cash();
            wallet.setCash(cash);
        }
        return cash;
    }
}
