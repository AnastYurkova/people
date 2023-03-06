package com.ku.people.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "Information for list of users")
public class UserListDto {
    @Schema(description = "User identifier")
    private Long id;

    @Schema(description = "Username")
    private String username;

    @Schema(description = "User's surname")
    private String surname;

    @Schema(description = "User's name")
    private String name;
}

