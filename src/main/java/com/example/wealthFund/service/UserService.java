package com.example.wealthFund.service;

import com.example.wealthFund.dto.UserDto;
import com.example.wealthFund.exception.WealthFundException;
import com.example.wealthFund.mapper.UserMapper;
import com.example.wealthFund.repository.UserRepository;
import com.example.wealthFund.repository.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper,UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public UserDto addNewUser(String name) {

        if (isTheUserNameContainsWhiteSpaces(name)){
            logger.info("please enter the name without whitespace");
            throw new WealthFundException( "please enter the name without whitespace");
        }
        if (isTheUserNameNotAcceptableLength(name)){
            logger.info("the user name should contain from 3 to 16 characters");
            throw new WealthFundException("the user name should contain from 3 to 16 characters");
        }
        if (userRepository.existsByUserName(name)) {
            logger.info("A user named " + name + " already exists. Enter a different name.");
            throw new WealthFundException("A user named " + name + " already exists. Enter a different name.");
        }
        else {
            logger.info("User input successful");

            UserDto user = new UserDto(name);
            userRepository.save(UserMapper.INSTANCE.userDtoToUser(user));
            return user;
        }
    }

    public boolean deleteUser(String name){
        if (userRepository.existsByUserName(name))
        {
            new User();
            User user = userRepository.findByName(name);
            System.out.println("A user named " + name + " exists. User has been delete");

            logger.info("User has been delete");
            userRepository.delete(user);
            return true;
        }
        else{
            logger.info("This user doesn't exists.");
            throw new WealthFundException("This user doesn't exists.");
        }
    }

    public List<UserDto> getUsers() {
        List<UserDto> userDtoList;
         userDtoList = UserMapper.INSTANCE.userListToUserDtoList(userRepository.findAll());
        return userDtoList;
    }

    public boolean isTheUserNameContainsWhiteSpaces(String userName){
        for(int i = 0; i < userName.length(); i++){
            if(Character.isWhitespace(userName.charAt(i))){
                return true;
            }
        }return false;
    }
    public boolean isTheUserNameNotAcceptableLength(String userName){
        if(userName.length() >= 3 && userName.length() <= 16){
            return false;
        }return true;
    }
}
