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
        return roleRepository.findById(id);
    }

    public List<RoleListDto> findAll() {
        return roleRepository.findAll();
    }

    public RoleSaveDto save(RoleSaveDto roleSaveDto) {
        return roleRepository.save(roleSaveDto);
    }

    public void update(RoleSaveDto roleSaveDto) {
        roleRepository.update(roleSaveDto);
    }

    public void delete(Long id) {
        roleRepository.delete(id);
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
}
