package com.example.wealthFund;

import com.example.wealthFund.repository.entity.User;
import com.example.wealthFund.restController.UserController;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserControllerTest {

    //BDD
    @Test
    public void get_Users_withBDD(){
        //given
        UserController userController = mock(UserController.class);
        given(userController.getUsers()).willReturn(prepareMockData());
        //when
        List<User> users = userController.getUsers();
        //then
        Assert.assertThat(users, Matchers.hasSize(2));
    }

    @Test
    public void get_Users(){
        //given
        UserController userController = mock(UserController.class);
        //when
        when(userController.getUsers()).thenReturn(prepareMockData());
        //then
        Assert.assertThat(userController.getUsers(), Matchers.hasSize(2));
    }

    private List<User> prepareMockData(){

        List<User> users = new ArrayList<>();
        users.add(new User("Piotr"));
        users.add(new User("Patryk"));
        return users;
    }
}
