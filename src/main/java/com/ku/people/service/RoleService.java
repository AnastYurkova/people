package com.ku.people.service;

import com.ku.people.dto.RoleDto;
import com.ku.people.dto.RoleListDto;
import com.ku.people.dto.RoleSaveDto;
import com.ku.people.entity.Role;
import com.ku.people.mapper.RoleMapper;
import com.ku.people.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private RoleRepository roleRepository;


    public RoleDto findById(Long id) {
        Role role = roleRepository.findById(id).get();
        return RoleMapper.toDto(role);
    }

    public List<RoleListDto> findAll() {
        List<Role> roles = roleRepository.findAll();
        return RoleMapper.toListDto(roles);
    }

    public Role save(RoleSaveDto roleSaveDto) {
        Role role = RoleMapper.fromSaveDto(roleSaveDto);
        return roleRepository.save(role);
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}
