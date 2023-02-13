package com.ku.people.service;


import com.ku.people.entity.User;
import com.ku.people.repository.hibernate.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void update(User user) {
        userRepository.update(user);
    }

    public void delete(Long id) {
        userRepository.delete(id);
    }

}
