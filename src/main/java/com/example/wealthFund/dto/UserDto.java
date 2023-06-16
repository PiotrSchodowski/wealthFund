package com.example.wealthFund.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {

    private String name;

    public UserDto(String name){
        this.name = name;
    }
}
