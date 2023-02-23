package com.ku.people.service;

import com.ku.people.entity.Authority;
import com.ku.people.repository.AuthorityRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public Optional<Authority> findById(Long id) {
        return authorityRepository.findById(id);
    }

    public List<Authority> findAll() {
        return authorityRepository.findAll();
    }

    public void save(Authority relationship) {
        authorityRepository.save(relationship);
    }

    public void update(Authority relationship) {
        authorityRepository.save(relationship);
    }

    public void delete(Authority authority) {
        authorityRepository.delete(authority);
    }

}
