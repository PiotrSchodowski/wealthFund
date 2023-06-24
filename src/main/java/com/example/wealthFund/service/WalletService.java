package com.example.wealthFund.service;

import com.example.wealthFund.dto.WalletDto;
import com.example.wealthFund.exception.UserNotExistException;
import com.example.wealthFund.exception.WealthFundSingleException;
import com.example.wealthFund.repository.UserRepository;
import com.example.wealthFund.repository.WalletRepository;
import com.example.wealthFund.repository.entity.User;
import com.example.wealthFund.repository.entity.Wallet;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
public class WalletService {
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final TextValidator textValidator;
    private final UserService userService;

    public WalletService(UserRepository userRepository, WalletRepository walletRepository,TextValidator textValidator,UserService userService) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
        this.textValidator = textValidator;
        this.userService = userService;
    }
    public WalletDto addNewWallet(String userName, String walletName, String currency) {
        textValidator.checkTextValidity(userName);
        textValidator.checkTextValidity(walletName);
        userService.validateUserExistenceThrowExceptionDoesNotExist(userName);
        validateUniqueWalletName(userName, walletName);

        User user = userRepository.findByName(userName);
        Wallet wallet = createWallet(walletName, currency, user);
        saveWalletWithUser(wallet, user);

        return new WalletDto(wallet.getName(), wallet.getCurrency());
    }
    @Transactional
    public boolean deleteWallet(String userName, String walletName) {
        textValidator.checkTextValidity(userName);
        textValidator.checkTextValidity(walletName);
        userService.validateUserExistenceThrowExceptionDoesNotExist(userName);

        User user = userRepository.findByName(userName);
        Set<Wallet> wallets = user.getWallets();
        Wallet walletToRemove = findWalletByName(wallets, walletName);

        if (walletToRemove != null) {
            wallets.remove(walletToRemove);
            user.setWallets(wallets);

            userRepository.save(user);
            walletRepository.delete(walletToRemove);
            return true;
        }
        throw new WealthFundSingleException("This wallet does not exist");
    }

    protected Wallet getWalletByName(User user, String walletName) {
        textValidator.checkTextValidity(walletName);
        validateWalletExistenceThrowExceptionDoesNotExist(user.getName(), walletName);
        return findWalletByName(user.getWallets(), walletName);
    }
    protected Wallet findWalletByName(Set<Wallet> wallets, String walletName) {
        for (Wallet wallet : wallets) {
            if (wallet.getName().equalsIgnoreCase(walletName)) {
                return wallet;
            }
        }
        return null;
    }
    private void validateUniqueWalletName(String userName, String walletName) {
        if (walletRepository.existsByWalletNameAndUserName(walletName, userName)) {
            throw new WealthFundSingleException("This name of wallet already exists");
        }
    }
    private Wallet createWallet(String walletName, String currency, User user) {
        Wallet wallet = new Wallet();
        wallet.setName(walletName);
        wallet.setCurrency(currency);
        wallet.setUser(user);
        return wallet;
    }
    private void saveWalletWithUser(Wallet wallet, User user) {
        walletRepository.save(wallet);
        Set<Wallet> wallets = user.getWallets();
        wallets.add(wallet);
        user.setWallets(wallets);
        userRepository.save(user);
    }
    protected void validateWalletExistenceThrowExceptionDoesNotExist(String userName, String walletName) {
        if (!walletRepository.existsByWalletNameAndUserName(walletName, userName)) {
            throw new WealthFundSingleException("This wallet does not exist");
        }
    }
}
