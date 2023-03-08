package com.ku.people.dto;

import com.ku.people.entity.RelationshipType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "Information for list of details")
public class DetailListDto {
    @Schema(description = "Detail id", requiredMode = Schema.RequiredMode.REQUIRED, example = "67")
    private Long id;

    @Schema(description = "Relationship type", requiredMode = Schema.RequiredMode.REQUIRED, example = "WIFE")
    private RelationshipType type;
}

