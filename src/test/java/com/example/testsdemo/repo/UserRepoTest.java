package com.example.testsdemo.repo;

import com.example.testsdemo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @DataJpaTest provides some standard setup needed for testing the persistence layer:
 *
 * configuring H2, an in-memory database
 * setting Hibernate, Spring Data, and the DataSource
 * performing an @EntityScan
 * turning on SQL logging
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepoTest {


    @Autowired
    private UserRepo userRepo;

    @Test
    public void findByName() {
        User user = new User();
        user.setAge(1);
        String name = "name for test.";
        user.setName(name);
        userRepo.save(user);
        assertThat(user).isNotNull();
        assertThat(user.getUserId()).isGreaterThan(0);

        List<User> users = userRepo.findByName(name);
        assertThat(users).isNotEmpty();
    }

}