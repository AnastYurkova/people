package com.ku.people.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSaveDto {
    private String username;
    private String surname;
    private String name;
    private String password;
}
