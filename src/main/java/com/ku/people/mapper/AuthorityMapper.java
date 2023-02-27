package com.ku.people.mapper;

import com.ku.people.dto.AuthorityDto;
import com.ku.people.dto.AuthorityListDto;
import com.ku.people.dto.AuthoritySaveDto;
import com.ku.people.entity.Authority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class AuthorityMapper {

    public AuthorityDto toDto(Authority authority) {
        AuthorityDto authorityDto = new AuthorityDto();
        authorityDto.setId(authority.getId());
        authorityDto.setAuthorityName(authority.getAuthorityName());
        authorityDto.setRoles(authority.getRoles());
        return authorityDto;
    }

    public List<AuthorityListDto> toListDto(List<Authority> authorities) {
        List<AuthorityListDto> authorityListDtos = new ArrayList<>();
        for (Authority authority : authorities) {
            AuthorityListDto authorityListDto = new AuthorityListDto();
            authorityListDto.setId(authority.getId());
            authorityListDto.setAuthorityName(authority.getAuthorityName());
            authorityListDtos.add(authorityListDto);
        }
        return authorityListDtos;
    }

    public Authority fromSaveDto(AuthoritySaveDto authoritySaveDto) {
        Authority authority = new Authority();
        authority.setAuthorityName(authoritySaveDto.getAuthorityName());
        authority.setRoles(new HashSet<>());
        return authority;
    }
}
