package com.ku.people.config;


import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.ku.people")
public class AppConfig {

    @Bean
    public SessionFactory sessionFactory( ){
        return new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
    }

}
