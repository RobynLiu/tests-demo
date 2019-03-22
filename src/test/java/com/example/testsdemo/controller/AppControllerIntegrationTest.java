package com.example.testsdemo.controller;

import com.example.testsdemo.TestsDemoApplication;
import com.example.testsdemo.repo.UserRepo;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = TestsDemoApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AppControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepo userRepo;

    private final String userName = "test_user";

    @Before
    public void setup() {
        // clean data
        userRepo.deleteAll();
    }


    @Test
    public void a_saveUser() throws Exception {
        mvc.perform(post("/users")
                .param("name", userName))
                .andExpect(status().isOk());
    }

    @Test
    public void b_getUsers() throws Exception {
        // 1. 先插入数据
        // Note: 手动调用，因为setup()方法在每个test method执行前都会被调用
        this.a_saveUser();
        assertThat(userRepo.findByName(userName)).isNotEmpty();

        // 2. 再测试
        mvc.perform(get("/users")
                .param("name", userName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }
}