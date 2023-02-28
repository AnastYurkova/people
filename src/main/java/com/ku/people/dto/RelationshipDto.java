package com.ku.people.dto;

import com.ku.people.entity.RelationshipStatus;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.Set;

@Data
@Accessors(chain = true)
public class RelationshipDto {
    private Long id;
    private LocalDate createdAtUtc;
    private RelationshipStatus status;
    private Set<DetailListDto> details;
}
