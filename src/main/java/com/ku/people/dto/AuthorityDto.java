package com.ku.people.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@Accessors(chain = true)
@Schema(description = "Authority Dto")
public class AuthorityDto {
    @Schema(description = "User id", requiredMode = Schema.RequiredMode.REQUIRED, example = "67")
    private Long id;

    @Schema(description = "Authority name", requiredMode = Schema.RequiredMode.REQUIRED, example = "role_write")
    private String authorityName;

    @Schema(description = "Roles", requiredMode = Schema.RequiredMode.REQUIRED)
    private Set<RoleListDto> roles;
}

