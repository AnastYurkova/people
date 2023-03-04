package com.ku.people.controller;

import com.ku.people.dto.UserDto;
import com.ku.people.dto.UserListDto;
import com.ku.people.dto.UserSaveDto;
import com.ku.people.dto.UserUpdateDto;
import com.ku.people.entity.User;
import com.ku.people.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @PostMapping
    public User save(@RequestBody UserSaveDto userSaveDto) {
        return userService.save(userSaveDto);
    }

    @GetMapping("/{id}")
    public UserDto findById(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

    @GetMapping
    public List<UserListDto> findAll() {
        return userService.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }

    @PutMapping
    public boolean update(@RequestBody UserUpdateDto userUpdateDto) {
        userService.update(userUpdateDto);
        return true;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
