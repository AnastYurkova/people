package com.ku.people.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AuthoritySaveDto {
    private String authorityName;
}
