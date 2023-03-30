package com.ku.people.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "Information about saving authority")
public class AuthoritySaveDto {
    @Schema(description = "Authority id", requiredMode = Schema.RequiredMode.REQUIRED, example = "67")
    private Long id;

    @Schema(description = "Authority name", requiredMode = Schema.RequiredMode.REQUIRED, example = "role_write")
    private String authorityName;
}

