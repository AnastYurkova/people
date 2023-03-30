package com.ku.people.dto;

import com.ku.people.entity.RelationshipStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.Set;

@Data
@Accessors(chain = true)
@Schema(description = "Relationship Dto")
public class RelationshipDto {
    @Schema(description = "Relationship id", requiredMode = Schema.RequiredMode.REQUIRED, example = "67")
    private Long id;

    @Schema(description = "Created at utc", requiredMode = Schema.RequiredMode.REQUIRED, example = "2022-01-01")
    private LocalDate createdAtUtc;

    @Schema(description = "Relationship status", requiredMode = Schema.RequiredMode.REQUIRED, example = "OK")
    private RelationshipStatus status;

    @Schema(description = "Details", requiredMode = Schema.RequiredMode.REQUIRED)
    private Set<DetailListDto> details;
}

