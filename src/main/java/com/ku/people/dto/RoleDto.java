package com.ku.people.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@Accessors(chain = true)
public class RoleDto {
    private Long id;
    private String name;
    private Set<UserListDto> users;
    private Set<AuthorityListDto> authorities;
}
