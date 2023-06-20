package com.example.wealthFund.serviceTests;

import com.example.wealthFund.exception.WealthFundException;
import com.example.wealthFund.repository.UserRepository;
import com.example.wealthFund.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private final UserRepository userRepository;

    public User addNewUser(String name) {

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
            User user = new User(name);
            logger.info("User input successful");
            return userRepository.save(user);
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

    public List<User> getUsers() {
        return userRepository.findAll();
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
