package com.ku.people.dto;

import com.ku.people.entity.RelationshipStatus;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@Accessors(chain = true)
public class RelationshipListDto {
    private Long id;
    private LocalDate createdAtUtc;
    private RelationshipStatus status;
}

