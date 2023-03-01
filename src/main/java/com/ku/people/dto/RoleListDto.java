package com.ku.people.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RoleListDto {
    private Long id;
    private String name;
}

