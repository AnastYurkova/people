package com.ku.gateway.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Optional;

@Data
@Accessors(chain = true)
public class UserFilter {
    @Schema(description = "Name", requiredMode = Schema.RequiredMode.REQUIRED, example = "Anastasia")
    private Optional<String> name;

    @Schema(description = "Surname", requiredMode = Schema.RequiredMode.REQUIRED, example = "Yurkova")
    private Optional<String> surname;

    @Schema(description = "Username", requiredMode = Schema.RequiredMode.REQUIRED, example = "nst.yrk")
    private Optional<String> username;

    private Optional<Integer> limit;

    private Optional<Integer> offset;

}