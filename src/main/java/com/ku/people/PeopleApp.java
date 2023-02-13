package com.ku.people;

import com.ku.people.config.AppConfig;
import com.ku.people.repository.hibernate.UserRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PeopleApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.ku.people");
        UserRepository bean = context.getBean(UserRepository.class);
        System.out.println(bean.findAll());
    }
}
