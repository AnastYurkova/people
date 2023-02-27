package com.ku.people.dto;

import com.ku.people.entity.RelationshipStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelationshipListDto {
    private Long id;
    private LocalDate createdAtUtc;
    private RelationshipStatus status;
}
