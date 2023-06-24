package com.example.wealthFund.exception;

public class InsufficientFundsException extends RuntimeException{
    public static final String MESSAGE = "insufficient funds in the account!";
    public static final String MESSAGE2 = " You are trying to trade for an amount: ";
    public static final String MESSAGE3 = " When you have in your account: ";
    public static final String MESSAGE4 = " ";

    public InsufficientFundsException(float valueInTheAccount, float subtractedValue, String currency){
        super(MESSAGE
                + MESSAGE2
                + subtractedValue
                + MESSAGE4
                + currency
                + MESSAGE3
                + valueInTheAccount
                + MESSAGE4
                + currency);
    }
}
