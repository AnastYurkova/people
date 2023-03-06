package com.ku.people.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@Accessors(chain = true)
@Schema(description = "Information about user")
public class UserDto {
    @Schema(description = "User identifier")
    private Long id;

    @Schema(description = "Username")
    private String username;

    @Schema(description = "User's surname")
    private String surname;

    @Schema(description = "User's name")
    private String name;

    @Schema(description = "List of user roles")
    private Set<RoleListDto> roles;

    @Schema(description = "Additional information about user")
    private Set<DetailListDto> details;
}

