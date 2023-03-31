package com.ku.people.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserFilter {
    @Schema(description = "Name", requiredMode = Schema.RequiredMode.REQUIRED, example = "Anastasia")
    private String name;

    @Schema(description = "Surname", requiredMode = Schema.RequiredMode.REQUIRED, example = "Yurkova")
    private String surname;

    @Schema(description = "Username", requiredMode = Schema.RequiredMode.REQUIRED, example = "nst.yrk")
    private String username;

    private Integer limit;

    private Integer offset;

}
