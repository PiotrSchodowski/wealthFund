package com.example.wealthFund.serviceTests;

import com.example.wealthFund.exception.WealthFundException;
import com.example.wealthFund.repository.UserRepository;
import com.example.wealthFund.repository.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userRepository;

    @Test
    public void should_ReturnTrueIfUserNameContainsWhitespace() {

        boolean firstCase = userService.isTheUserNameContainsWhiteSpaces("srg");
        boolean secondCase = userService.isTheUserNameContainsWhiteSpaces("sr g");
        boolean thirdCase = userService.isTheUserNameContainsWhiteSpaces(" srg ");

        Assert.assertEquals(false, firstCase);
        Assert.assertEquals(true, secondCase);
        Assert.assertEquals(true, thirdCase);
    }
    @Test
    public void should_ReturnTrueIfUserNameNotAcceptableLength() {

        boolean firstCase = userService.isTheUserNameNotAcceptableLength("OlaMaKota");
        boolean secondCase = userService.isTheUserNameNotAcceptableLength("Ol");
        boolean thirdCase = userService.isTheUserNameNotAcceptableLength("OlaMaKotaAKotMaOle");

        Assert.assertEquals(false, firstCase);
        Assert.assertEquals(true, secondCase);
        Assert.assertEquals(true, thirdCase);
    }

    @Test
    public void should_ThrowExceptionWhenAddingUserWithInvalidName(){

        assertThrows(WealthFundException.class, () -> {
            userService.addNewUser("safg  ");
        });
    }

    @Test
    public void should_NotThrowExceptionWhenAddingUserWithValidName(){

        when(userRepository.save(any())).thenReturn(any());
        User actual = userService.addNewUser("NameIsOk");
        assertNull(actual);
    }
}