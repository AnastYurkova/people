package com.ku.people.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserSaveDto {
    private String username;
    private String surname;
    private String name;
    private String password;
}

