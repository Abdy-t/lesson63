package com.example.lesson60.controller;

import com.example.lesson60.model.User;
import com.example.lesson60.service.UserAuthService;
import com.example.lesson60.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {
    private final UserService userService;
    private final UserAuthService userAuthService;

    public UserController(UserService userService, UserAuthService userAuthService) {
        this.userService=userService;
        this.userAuthService=userAuthService;
    }
    @RequestMapping("/demo/getUser")
    public User getUser() {
        User user = userService.getUser();
        return user;
    }

    @PostMapping("/registration")
    public User createUser(@RequestParam("email") String email, @RequestParam("name") String name,
                                @RequestParam("login") String login, @RequestParam("password") String password) {

        User u = new User(email, password);
        userService.saveUser(u);
        return u;
    }

    @PostMapping("/login")
    public UserDetails checkLogin(@RequestParam("login") String login, @RequestParam("password") String password) {
        return userAuthService.loadUserByUsername(login);
    }
}
