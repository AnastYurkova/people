package com.ku.gateway.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Optional;

@Data
@Accessors(chain = true)
public class UserFilter {

    private Optional<String> name;

    private Optional<String> surname;

    private Optional<String> username;

    private Integer limit;

    private Integer offset;

}