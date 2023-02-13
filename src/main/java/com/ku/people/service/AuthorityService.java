package com.ku.people.service;


import com.ku.people.entity.Authority;
import com.ku.people.repository.hibernate.AuthorityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public Authority findById(Long id) {
        return authorityRepository.findById(id);
    }

    public List<Authority> findAll() {
        return authorityRepository.findAll();
    }

    public void save(Authority relationship) {
        authorityRepository.save(relationship);
    }

    public void update(Authority relationship) {
        authorityRepository.update(relationship);
    }

    public void delete(Long id) {
        authorityRepository.delete(id);
    }

}
