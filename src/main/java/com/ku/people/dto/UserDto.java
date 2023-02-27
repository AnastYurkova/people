package com.ku.people.dto;

import com.ku.people.entity.Detail;
import com.ku.people.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String surname;
    private String name;
    private Set<Role> roles;
    private Set<Detail> details;
}
