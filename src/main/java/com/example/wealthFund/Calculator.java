package com.example.wealthFund;

public class Calculator {

    public int add(int number1, int number2){
        return number1+number2;
    }

    public double divide(double number1, double number2){
        if(number2 == 0){
            throw new ArithmeticException("number cant not by divide by 0");
        }
        return number1/number2;
    }
}
