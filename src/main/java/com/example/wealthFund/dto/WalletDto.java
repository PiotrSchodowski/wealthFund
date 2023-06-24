package com.example.wealthFund.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WalletDto {

    String name;
    String currency;

    public  WalletDto(String name, String currency){
        this.name = name;
        this.currency = currency;
    }
}
