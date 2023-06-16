package com.example.wealthFund.service;

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

    public User addNewUser(String name) {

        if (userRepository.existsByUserName(name))
        {
            throw new IllegalArgumentException("A user named " + name + " already exists. Enter a different name.");
        }

        User user = new User(name);
        return userRepository.save(user);
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
}
