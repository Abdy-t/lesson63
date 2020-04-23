package com.example.lesson60.service;

import com.example.lesson60.model.User;
import com.example.lesson60.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository=userRepository;
    }

    public User saveUser(User user) {
        userRepository.save(user);
        return user;
    }

    public User getUser() {
        // get current authenticated user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var a =  userRepository.findByEmail(auth.getName()).get();
        System.out.println("getUser method: " + a.getEmail());
        return a;
    }
}
