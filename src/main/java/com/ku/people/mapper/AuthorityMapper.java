package com.ku.people.mapper;

import com.ku.people.dto.AuthorityDto;
import com.ku.people.dto.AuthorityListDto;
import com.ku.people.dto.AuthoritySaveDto;
import com.ku.people.entity.Authority;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AuthorityMapper {
    public static AuthorityDto toDto(Authority authority) {
        return new AuthorityDto()
                .setId(authority.getId())
                .setAuthorityName(authority.getAuthorityName())
                .setRoles(RoleMapper.toListDto(authority.getRoles()));
    }

    public static List<AuthorityListDto> toListDto(List<Authority> authorities) {
        List<AuthorityListDto> authorityListDtos = new ArrayList<>();
        for (Authority authority : authorities) {
            authorityListDtos.add(toListDto(authority));
        }
        return authorityListDtos;
    }

    public static Set<AuthorityListDto> toListDto(Set<Authority> authorities) {
        List<AuthorityListDto> authorityListDtos = new ArrayList<>();
        for (Authority authority : authorities) {
            authorityListDtos.add(toListDto(authority));
        }
        return new HashSet<>(authorityListDtos);
    }

    public static AuthorityListDto toListDto(Authority authority) {
        return new AuthorityListDto()
                .setId(authority.getId())
                .setAuthorityName(authority.getAuthorityName());
    }

    public static Authority fromSaveDto(AuthoritySaveDto authoritySaveDto) {
        return new Authority()
                .setAuthorityName(authoritySaveDto.getAuthorityName())
                .setRoles(new HashSet<>());
    }
}
