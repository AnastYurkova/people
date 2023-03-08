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
import java.util.Optional;

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

    public void update(AuthoritySaveDto authoritySaveDto) {
        Authority authority = AuthorityMapper.fromSaveDtoForUpdate(authoritySaveDto);
        authorityRepository.save(authority);
    }

    public void delete(Long id) {
        Optional<Authority> optional = authorityRepository.findById(id);
        if (optional.isPresent()) {
            Authority authority = optional.get();
            authorityRepository.delete(authority);
        }
    }

    @Autowired
    public void setAuthorityRepository(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }
}
