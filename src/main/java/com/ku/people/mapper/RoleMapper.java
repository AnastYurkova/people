package com.ku.people.mapper;

import com.ku.people.dto.RoleDto;
import com.ku.people.dto.RoleListDto;
import com.ku.people.dto.RoleSaveDto;
import com.ku.people.entity.Role;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class RoleMapper {
    public RoleDto toDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        roleDto.setAuthorities(role.getAuthorities());
        roleDto.setUsers(role.getUsers());
        return roleDto;
    }

    public List<RoleListDto> toListDto(List<Role> roles) {
        List<RoleListDto> roleListDtos = new ArrayList<>();
        for (Role role : roles) {
            RoleListDto roleListDto = new RoleListDto();
            roleListDto.setId(role.getId());
            roleListDto.setName(role.getName());
            roleListDtos.add(roleListDto);
        }
        return roleListDtos;
    }

    public Role fromSaveDto(RoleSaveDto roleSaveDto) {
        Role role = new Role();
        role.setName(roleSaveDto.getName());
        role.setUsers(new HashSet<>());
        role.setAuthorities(new HashSet<>());
        return role;
    }
}
