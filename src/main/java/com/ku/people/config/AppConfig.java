package com.ku.people.config;


import com.ku.people.entity.Authority;
import com.ku.people.entity.Detail;
import com.ku.people.entity.Relationship;
import com.ku.people.entity.Role;
import com.ku.people.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Properties;

@Configuration
@ComponentScan("com.ku.people")
@PropertySource("application.yml")
public class AppConfig {

    @Value("${connection.url}")
    private String url;
    @Value("${connection.username}")
    private String user;
    @Value("${connection.password}")
    private String password;
    @Value("${connection.driver_class}")
    private String driver;

    @Value("${hibernate.dialect}")
    private String dialect;
    @Bean
    public SessionFactory sessionFactory() {

        try {
            org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
            Properties settings = new Properties();
            settings.put(Environment.DRIVER, driver);
            settings.put(Environment.URL, url);
            settings.put(Environment.USER, user);
            settings.put(Environment.PASS, password);
            settings.put(Environment.DIALECT, dialect);

            configuration.setProperties(settings);


            configuration.addAnnotatedClass(User.class);
            configuration.addAnnotatedClass(Authority.class);
            configuration.addAnnotatedClass(Relationship.class);
            configuration.addAnnotatedClass(Detail.class);
            configuration.addAnnotatedClass(Role.class);


            var serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            return configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

