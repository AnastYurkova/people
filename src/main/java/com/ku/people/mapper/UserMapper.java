package com.ku.people.mapper;

import com.ku.people.dto.UserDto;
import com.ku.people.dto.UserListDto;
import com.ku.people.dto.UserSaveDto;
import com.ku.people.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setSurname(user.getSurname());
        userDto.setName(user.getName());
        userDto.setRoles(user.getRoles());
        userDto.setDetails(user.getDetails());
        return userDto;
    }

    public List<UserListDto> toListDto(List<User> users) {
        List<UserListDto> userListDtos = new ArrayList<>();
        for (User user : users) {
            UserListDto userListDto = new UserListDto();
            userListDto.setId(user.getId());
            userListDto.setUsername(user.getUsername());
            userListDto.setSurname(user.getSurname());
            userListDto.setName(user.getName());
            userListDtos.add(userListDto);
        }
        return userListDtos;
    }

    public User fromSaveDto(UserSaveDto userSaveDto) {
        User user = new User();
        user.setName(userSaveDto.getName());
        user.setSurname(userSaveDto.getSurname());
        user.setUsername(userSaveDto.getUsername());
        user.setPassword(userSaveDto.getPassword());
        user.setDetails(new HashSet<>());
        user.setRoles(new HashSet<>());
        return user;
    }
}
