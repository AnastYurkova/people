package com.ku.people.mapper;

import com.ku.people.dto.RelationshipDto;
import com.ku.people.dto.RelationshipListDto;
import com.ku.people.dto.RelationshipSaveDto;
import com.ku.people.entity.Relationship;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class RelationshipMapper {
    public RelationshipDto toDto(Relationship relationship) {
        RelationshipDto relationshipDto = new RelationshipDto();
        relationshipDto.setId(relationship.getId());
        relationshipDto.setCreatedAtUtc(relationship.getCreatedAtUtc());
        relationshipDto.setStatus(relationship.getStatus());
        relationshipDto.setDetails(relationship.getDetails());
        return relationshipDto;
    }

    public List<RelationshipListDto> toListDto(List<Relationship> relationships) {
        List<RelationshipListDto> relationshipListDtos = new ArrayList<>();
        for (Relationship relationship : relationships) {
            RelationshipListDto relationshipListDto = new RelationshipListDto();
            relationshipListDto.setId(relationship.getId());
            relationshipListDto.setCreatedAtUtc(relationship.getCreatedAtUtc());
            relationshipListDto.setStatus(relationship.getStatus());
            relationshipListDtos.add(relationshipListDto);
        }
        return relationshipListDtos;
    }

    public Relationship fromSaveDto(RelationshipSaveDto relationshipSaveDto) {
        Relationship relationship = new Relationship();
        relationship.setCreatedAtUtc(relationshipSaveDto.getCreatedAtUtc());
        relationship.setStatus(relationshipSaveDto.getStatus());
        relationship.setDetails(new HashSet<>());
        return relationship;
    }
}
