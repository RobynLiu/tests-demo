package com.example.testsdemo.controller;

import com.example.testsdemo.entity.User;
import com.example.testsdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AppController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/users")
    public User saveUser(String name) {
        return userService.saveUser(name);
    }

    @GetMapping(value = "/users")
    public List<User> getUsers(String name) {
        return userService.getUsers(name);
    }

}
