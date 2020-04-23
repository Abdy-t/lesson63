package com.example.lesson60.controller;

import com.example.lesson60.model.Publication;
import com.example.lesson60.model.User;
import com.example.lesson60.model.Comment;
import com.example.lesson60.service.ComService;
import com.example.lesson60.service.PubService;
import com.example.lesson60.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PubController {
    private final UserService userService;
    private final PubService pubService;
    private final ComService comService;

    public PubController(UserService userService, PubService pubService, ComService comService){
        this.userService=userService;
        this.pubService=pubService;
        this.comService=comService;
    }

    @GetMapping("/getPosts")
    public List<Publication> getPosts(){
        return pubService.getPublications();
    }

    @GetMapping("/getUser")
    public User getUser(){
        User user = userService.getUser();
        return user;
    }

    @GetMapping("/getComments")
    public List<Comment> getComments(){
        return comService.getComments();
    }
}
