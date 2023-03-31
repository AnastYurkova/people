package com.ku.gateway.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Optional;

@Data
@Accessors(chain = true)
@Schema(description = "Information about saving user")
public class UserSaveDto {
    @Schema(description = "User id", requiredMode = Schema.RequiredMode.REQUIRED, example = "67")
    private Long id;

    @Schema(description = "Username", requiredMode = Schema.RequiredMode.REQUIRED, example = "nst.yrk")
    private String username;

    @Schema(description = "Surname", requiredMode = Schema.RequiredMode.REQUIRED, example = "Yurkova")
    private String surname;

    @Schema(description = "Name", requiredMode = Schema.RequiredMode.REQUIRED, example = "Anastasia")
    private String name;

    @Schema(description = "Password", requiredMode = Schema.RequiredMode.REQUIRED, example = "12345678")
    private String password;
}
