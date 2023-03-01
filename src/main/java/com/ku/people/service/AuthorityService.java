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
        Authority authority = authorityRepository.findById(id).get();
        return AuthorityMapper.toDto(authority);
    }

    public List<AuthorityListDto> findAll() {
        List<Authority> authorities = authorityRepository.findAll();
        return AuthorityMapper.toListDto(authorities);
    }

    public Authority save(AuthoritySaveDto authoritySaveDto) {
        Authority authority = AuthorityMapper.fromSaveDto(authoritySaveDto);
        return authorityRepository.save(authority);
    }

    @Autowired
    public void setAuthorityRepository(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }
}
