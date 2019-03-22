package com.example.testsdemo.service.impl;

import com.example.testsdemo.service.EmployeeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Log4j2
public class EmployeeServiceImpl implements EmployeeService {

    @PostConstruct
    public void postConstruct() {
        log.info(this.getClass().getName() + " initialized.");
    }
}
