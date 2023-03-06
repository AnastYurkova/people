package com.ku.people.service;


import com.ku.people.dto.UserDto;
import com.ku.people.dto.UserListDto;
import com.ku.people.dto.UserSaveDto;
import com.ku.people.entity.User;
import com.ku.people.mapper.UserMapper;
import com.ku.people.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;


    public UserDto findById(Long id) {
        User user = userRepository.findById(id).get();
        return UserMapper.toDto(user);
    }

    public List<UserListDto> findAll() {
        List<User> users = userRepository.findAll();
        return UserMapper.toListDto(users);
    }

    public User save(UserSaveDto userSaveDto) {
        User user = UserMapper.fromSaveDto(userSaveDto);
        return userRepository.save(user);
    }

    public void update(UserSaveDto userSaveDto) {
        User updated = UserMapper.fromSaveDto(userSaveDto);
        userRepository.save(updated);
    }

    public void delete(Long id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


}
