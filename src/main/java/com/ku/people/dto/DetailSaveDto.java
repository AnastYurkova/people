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
public class DetailSaveDto {
    private RelationshipType type;
    private User user;
    private Relationship relationship;
}

