package com.example.testsdemo.controller;

import com.example.testsdemo.entity.User;
import com.example.testsdemo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @WebMvcTest also auto-configures MockMvc which offers a powerful way of
// easy testing MVC controllers without starting a full HTTP server.
@RunWith(SpringRunner.class)
@WebMvcTest
public class AppControllerTestCase {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void saveUser() {
    }

    @Test
    public void getUsers() throws Exception {
        when(userService.getUsers(anyString())).thenReturn(Collections.singletonList(new User()));

        mvc.perform(get("/users")
                .param("name", "a"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }
}