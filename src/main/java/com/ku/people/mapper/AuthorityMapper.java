package com.ku.people.mapper;

import com.ku.people.dto.AuthorityDto;
import com.ku.people.dto.AuthorityListDto;
import com.ku.people.dto.AuthoritySaveDto;
import com.ku.people.entity.Authority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AuthorityMapper {
    public static AuthorityDto toDto(Authority authority) {
        return new AuthorityDto()
                .setId(authority.getId())
                .setAuthorityName(authority.getAuthorityName())
                .setRoles(RoleMapper.toListDto(authority.getRoles()));
    }

    public static List<AuthorityListDto> toListDto(List<Authority> authorities) {
        return authorities.stream()
                .map(AuthorityMapper::toListDto)
                .collect(Collectors.toList());
    }

    public static Set<AuthorityListDto> toListDto(Set<Authority> authorities) {
        return authorities.stream()
                .map(AuthorityMapper::toListDto)
                .collect(Collectors.toSet());
    }

    public static AuthorityListDto toListDto(Authority authority) {
        return new AuthorityListDto()
                .setId(authority.getId())
                .setAuthorityName(authority.getAuthorityName());
    }

    public static Authority fromSaveDto(AuthoritySaveDto authoritySaveDto) {
        return new Authority()
                .setAuthorityName(authoritySaveDto.getAuthorityName());
    }
}
