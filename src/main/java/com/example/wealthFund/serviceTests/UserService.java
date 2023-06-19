package com.example.wealthFund.serviceTests;

import com.example.wealthFund.repository.UserRepository;
import com.example.wealthFund.repository.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public String addNewUser(String name) {

        if (isTheUserNameContainsWhiteSpaces(name)){
            return "please enter the name without whitespace";
        }
        if (isTheUserNameNotEnoughLength(name)){
            return "the user name should contain from 3 to 16 characters";
        }
        if (userRepository.existsByUserName(name)) {
            return "A user named " + name + " already exists. Enter a different name.";
        }
        else {
            User user = new User(name);
            userRepository.save(user);
            return "User input successful";
        }
    }

    public String deleteUser(String name){
        if (userRepository.existsByUserName(name))
        {
            new User();
            User user = userRepository.findByName(name);
            System.out.println("A user named " + name + " exists. User has been delete");
            userRepository.delete(user);
            return "User has been delete";
        }
        return "This user doesn't exists.";

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
    public boolean isTheUserNameNotEnoughLength(String userName){
        if(userName.length() >= 3 && userName.length() <= 16){
            return false;
        }return true;
    }
}
