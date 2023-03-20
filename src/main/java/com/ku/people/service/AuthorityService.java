package com.ku.people.service;

import com.ku.people.dto.AuthorityDto;
import com.ku.people.dto.AuthorityListDto;
import com.ku.people.dto.AuthoritySaveDto;
import com.ku.people.entity.Authority;
import com.ku.people.mapper.AuthorityMapper;
import com.ku.people.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityService {

    private AuthorityRepository authorityRepository;

    public AuthorityDto findById(Long id) {
        return authorityRepository.findById(id);
    }

    public List<AuthorityListDto> findAll() {
        return authorityRepository.findAll();
    }

    public AuthoritySaveDto save(AuthoritySaveDto authoritySaveDto) {
        return authorityRepository.save(authoritySaveDto);
    }

    public void update(AuthoritySaveDto authoritySaveDto) {
        authorityRepository.update(authoritySaveDto);
    }

    public void delete(Long id) {
        authorityRepository.delete(id);
    }

    @Autowired
    public void setAuthorityRepository(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }
}
