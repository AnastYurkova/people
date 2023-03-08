package com.ku.people.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@Accessors(chain = true)
@Schema(description = "User Dto")
public class UserDto {
    @Schema(description = "User id", requiredMode = Schema.RequiredMode.REQUIRED, example = "67")
    private Long id;

    @Schema(description = "Username", requiredMode = Schema.RequiredMode.REQUIRED, example = "nst.yrk")
    private String username;

    @Schema(description = "Surname", requiredMode = Schema.RequiredMode.REQUIRED, example = "Yurkova")
    private String surname;

    @Schema(description = "Name", requiredMode = Schema.RequiredMode.REQUIRED, example = "Anastasia")
    private String name;

    @Schema(description = "Roles", requiredMode = Schema.RequiredMode.REQUIRED)
    private Set<RoleListDto> roles;

    @Schema(description = "Details",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private Set<DetailListDto> details;
}

