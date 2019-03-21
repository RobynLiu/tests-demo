package com.example.testsdemo.service.impl;

import com.example.testsdemo.entity.User;
import com.example.testsdemo.repo.UserRepo;
import com.example.testsdemo.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    /**
     * @see TestConfiguration annotation can be used on classes in src/test/java
     * to indicate that they should not be picked up by scanning.
     */
    @TestConfiguration
    static class UserServiceImplTestConfig {

        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }

    @Autowired
    private UserService userService;

    // mock UserRepo，并声明为可以被spring管理的bean
    @MockBean
    private UserRepo userRepo;


    @Test
    public void saveUser() {
        when(userRepo.save(any(User.class))).thenReturn(new User());
        assertThat(userService.saveUser("a")).isNotNull();
    }

    @Test
    public void getUsers() {
        assertThat(userService.getUsers("a")).isEmpty();

        User user = new User();
        String name = "test user";
        when(userRepo.findByName(eq(name))).thenReturn(Collections.singletonList(user));
        assertThat(userService.getUsers(name)).isNotEmpty();
    }
}