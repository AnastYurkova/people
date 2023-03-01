package com.ku.people.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@Accessors(chain = true)
public class AuthorityDto {
    private Long id;
    private String authorityName;
    private Set<RoleListDto> roles;
}

