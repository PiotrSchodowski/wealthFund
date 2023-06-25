package com.example.wealthFund.restController;

import com.example.wealthFund.service.CashService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CashController {

    private final CashService cashService;

    public CashController(CashService cashService){
        this.cashService = cashService;
    }

    @PostMapping("user/wallet/{userName}/{walletName}/{valueOfDeposit}")
    public boolean depositCashIntoTheWallet(@PathVariable String userName,
                                            @PathVariable String walletName,
                                            @PathVariable float valueOfDeposit){
        return cashService.depositCashIntoTheWallet(userName,walletName,valueOfDeposit);
    }

    @DeleteMapping("user/wallet/{userName}/{walletName}/{valueOfWithdraw}")
    public boolean withdrawCashFromTheWallet(@PathVariable String userName,
                                             @PathVariable String walletName,
                                             @PathVariable float valueOfWithdraw){
        return cashService.withdrawCashFromTheWallet(userName,walletName,valueOfWithdraw);
    }

}
