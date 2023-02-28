package com.ku.people.dto;

import com.ku.people.entity.RelationshipType;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DetailDto {
    private Long id;
    private RelationshipType type;
    private UserDto user;
    private RelationshipDto relationship;
}

