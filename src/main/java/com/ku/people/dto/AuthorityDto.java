package com.ku.people.dto;

import com.ku.people.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityDto {
    private Long id;
    private String authorityName;
    private Set<Role> roles;
}
