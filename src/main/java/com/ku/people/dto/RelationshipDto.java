package com.ku.people.dto;

import com.ku.people.entity.Detail;
import com.ku.people.entity.RelationshipStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelationshipDto {
    private Long id;
    private LocalDate createdAtUtc;
    private RelationshipStatus status;
    private Set<Detail> details;
}
