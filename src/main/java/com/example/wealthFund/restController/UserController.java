package com.example.wealthFund.restController;

import com.example.wealthFund.repository.UserRepository;
import com.example.wealthFund.repository.entity.User;
import com.example.wealthFund.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;

//    @ApiOperation(value = "Endpoint allowing to add new User")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Successfully added new User"),
//            @ApiResponse(code = 500, message = "This user name is already exists. Enter a different name.")})
    @PostMapping("/users")
    public User addNewUser(@RequestParam String name) {
        return userService.addNewUser(name);
    }

    @PostMapping("/users/1")
    public void updateUser() {
        throw new IllegalArgumentException("Not implemented yet!");
    }

    @DeleteMapping("/users/1")
    public String deleteUser(@RequestParam String name) {
        return userService.deleteUser(name);
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getUsers();
    }
}
