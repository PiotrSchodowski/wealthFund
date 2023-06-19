package com.example.wealthFund.serviceTests;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class UserServiceTest {

    @Test
    public void is_the_username_contains_whitespaces() {
        //given
        String userName1 = "  Piotr ";
        String userName2 = "Piotr    Schodowski";
        String userName3 = "PiotrSchodowski";

        //when
        boolean userName1ContainsWhiteSpaces = false;
        boolean userName2ContainsWhiteSpaces = false;
        boolean userName3ContainsWhiteSpaces = false;

        //then
        for (int i = 0; i < userName1.length(); i++) {
            if (Character.isWhitespace(userName1.charAt(i))) {
                userName1ContainsWhiteSpaces = true;
            }
        }
        for (int i = 0; i < userName2.length(); i++) {
            if (Character.isWhitespace(userName2.charAt(i))) {
                userName2ContainsWhiteSpaces = true;
            }
        }
        for (int i = 0; i < userName3.length(); i++) {
            if (Character.isWhitespace(userName3.charAt(i))) {
                userName3ContainsWhiteSpaces = true;
            }
        }
        //assert
        Assert.assertTrue(userName1ContainsWhiteSpaces);
        Assert.assertTrue(userName2ContainsWhiteSpaces);
        Assert.assertFalse(userName3ContainsWhiteSpaces);
    }
    @Test
    public void is_the_username_not_enough_length() {
        //given
        String username1 = "OlaMaKota";
        String username2 = "OlaMaKotaAKotMaAle";
        String username3 = "12";

        //when
        boolean userName1NotEnoughLength = username1.length() < 3 || username1.length() > 16;
        boolean userName2NotEnoughLength = username2.length() < 3 || username2.length() > 16;
        boolean userName3NotEnoughLength = username3.length() < 3 || username3.length() > 16;

        //assert
        Assert.assertFalse(userName1NotEnoughLength);
        Assert.assertTrue(userName2NotEnoughLength);
        Assert.assertTrue(userName3NotEnoughLength);
    }

}