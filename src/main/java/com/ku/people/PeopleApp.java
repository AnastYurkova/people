package com.ku.people;

import com.ku.people.repository.dataJPA.RoleRepository;
import com.ku.people.repository.dataJPA.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PeopleApp {
    public static void main(String[] args) {
        SpringApplication.run(PeopleApp.class, args);
    }
}
