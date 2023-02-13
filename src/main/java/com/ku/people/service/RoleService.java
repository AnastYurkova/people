package com.ku.people.service;


import com.ku.people.entity.Role;
import com.ku.people.repository.hibernate.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findById(Long id) {
        return roleRepository.findById(id);
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public void save(Role role) {
        roleRepository.save(role);
    }

    public void update(Role role) {
        roleRepository.update(role);
    }

    public void delete(Long id) {
        roleRepository.delete(id);
    }

}
