package com.ku.people;

import com.ku.people.repository.hibernate.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PeopleApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(PeopleApp.class, args);
        UserRepository bean = run.getBean(UserRepository.class);
        System.out.println(bean.findAll());

    }
}
