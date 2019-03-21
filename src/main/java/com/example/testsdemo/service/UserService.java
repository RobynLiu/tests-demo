package com.example.testsdemo.service;

import com.example.testsdemo.entity.User;

import java.util.List;

public interface UserService {

    User saveUser(String name);

    List<User> getUsers(String name);
}
