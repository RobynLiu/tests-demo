package com.example.testsdemo.service.impl;

import com.example.testsdemo.entity.User;
import com.example.testsdemo.repo.UserRepo;
import com.example.testsdemo.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User saveUser(String name) {
        User user = new User();
        user.setAge(name.length());
        user.setName(name);
        return userRepo.save(user);
    }

    @Override
    public List<User> getUsers(String name) {
        return userRepo.findByName(name);
    }

    @PostConstruct
    public void postConstruct() {
        log.info(this.getClass().getName() + " initialized.");
    }
}
