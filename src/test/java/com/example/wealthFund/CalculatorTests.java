package com.example.wealthFund;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTests {

    @Test
    public void should_add_two_numbers1() {
        //given
        Calculator calculator = new Calculator();
        //when
        int result = calculator.add(10, 15);
        //then
        Assert.assertEquals(result, 25);
    }

    @Test
    public void should_add_two_numbers2() {
        //given
        Calculator calculator = new Calculator();
        //then
        Assert.assertEquals(calculator.add(10, -15), -5);
        Assert.assertEquals(calculator.add(10, 0), 10);
        Assert.assertEquals(calculator.add(-10, -10), -20);

    }

    @Test
    public void should_divide_two_numbers() {
        //given
        Calculator calculator = new Calculator();
        //then
        Assertions.assertEquals(calculator.divide(10, 10), 1);
    }
}
