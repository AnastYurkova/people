package com.ku.people.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@Accessors(chain = true)
@Schema(description = "Role Dto")
public class RoleDto {
    @Schema(description = "Role id", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    private Long id;

    @Schema(description = "Name", requiredMode = Schema.RequiredMode.REQUIRED, example = "reader")
    private String name;

    @Schema(description = "Users", requiredMode = Schema.RequiredMode.REQUIRED)
    private Set<UserListDto> users;

    @Schema(description = "Authorities",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private Set<AuthorityListDto> authorities;
}

