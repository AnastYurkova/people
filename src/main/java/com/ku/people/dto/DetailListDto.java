package com.ku.people.dto;

import com.ku.people.entity.Relationship;
import com.ku.people.entity.RelationshipType;
import com.ku.people.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailListDto {
    private Long id;
    private RelationshipType type;
}

