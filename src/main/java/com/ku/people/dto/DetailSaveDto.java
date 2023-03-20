package com.ku.people.dto;

import com.ku.people.entity.RelationshipType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "Information about saving detail")
public class DetailSaveDto {
    @Schema(description = "Detail id", requiredMode = Schema.RequiredMode.REQUIRED, example = "67")
    private Long id;

    @Schema(description = "Relationship type", requiredMode = Schema.RequiredMode.REQUIRED, example = "WIFE")
    private RelationshipType type;

    @Schema(description = "User", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long userId;

    @Schema(description = "Relationship", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    private Long relationshipId;
}

