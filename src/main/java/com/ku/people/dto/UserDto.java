package com.ku.people.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@Accessors(chain = true)
public class UserDto {
    private Long id;
    private String username;
    private String surname;
    private String name;
    private Set<RoleListDto> roles;
    private Set<DetailListDto> details;
}
