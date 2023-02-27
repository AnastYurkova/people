package com.ku.people.dto;

import com.ku.people.entity.Authority;
import com.ku.people.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    private Long id;
    private String name;
    private Set<User> users;
    private Set<Authority> authorities;
}
