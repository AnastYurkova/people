package com.ku.people.mapper;

import com.ku.people.dto.RoleDto;
import com.ku.people.dto.RoleListDto;
import com.ku.people.dto.RoleSaveDto;
import com.ku.people.entity.Role;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RoleMapper {

    public static RoleDto toDto(Role role) {
        return new RoleDto()
                .setId(role.getId())
                .setName(role.getName())
                .setAuthorities(AuthorityMapper.toListDto(role.getAuthorities()))
                .setUsers(UserMapper.toListDto(role.getUsers()));
    }

    public static List<RoleListDto> toListDto(List<Role> roles) {
        return roles.stream()
                .map(RoleMapper::toListDto)
                .collect(Collectors.toList());
    }

    public static Set<RoleListDto> toListDto(Set<Role> roles) {
        return roles.stream()
                .map(RoleMapper::toListDto)
                .collect(Collectors.toSet());
    }

    public static RoleListDto toListDto(Role role) {
        return new RoleListDto()
                .setId(role.getId())
                .setName(role.getName());
    }

    public static Role fromSaveDto(RoleSaveDto roleSaveDto) {
        return new Role()
                .setName(roleSaveDto.getName());
    }

    public static Role fromSaveDtoForUpdate(RoleSaveDto roleSaveDto) {
        return new Role()
                .setId(roleSaveDto.getId())
                .setName(roleSaveDto.getName());
    }
}
