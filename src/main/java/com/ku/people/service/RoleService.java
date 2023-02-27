package com.ku.people.service;

import com.ku.people.dto.RoleDto;
import com.ku.people.dto.RoleListDto;
import com.ku.people.dto.RoleSaveDto;
import com.ku.people.entity.Role;
import com.ku.people.mapper.RoleMapper;
import com.ku.people.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleService {

    private RoleRepository roleRepository;

    private RoleMapper roleMapper;

    public RoleDto findById(Long id) {
        Role role = roleRepository.findById(id).get();
        return roleMapper.toDto(role);
    }

    public List<RoleListDto> findAll() {
        List<Role> roles = roleRepository.findAll();
        return roleMapper.toListDto(roles);
    }

    public Role save(RoleSaveDto roleSaveDto) {
        Role role = roleMapper.fromSaveDto(roleSaveDto);
        return roleRepository.save(role);
    }

}
