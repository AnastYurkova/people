package com.ku.people.mapper;

import com.ku.people.dto.RelationshipDto;
import com.ku.people.dto.RelationshipListDto;
import com.ku.people.dto.RelationshipSaveDto;
import com.ku.people.entity.Relationship;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RelationshipMapper {
    public static RelationshipDto toDto(Relationship relationship) {
        return new RelationshipDto()
                .setId(relationship.getId())
                .setCreatedAtUtc(relationship.getCreatedAtUtc())
                .setStatus(relationship.getStatus())
                .setDetails(DetailMapper.toListDto(relationship.getDetails()));
    }

    public static List<RelationshipListDto> toListDto(List<Relationship> relationships) {
        return relationships.stream()
                .map(RelationshipMapper::toListDto)
                .collect(Collectors.toList());
    }

    public static Set<RelationshipListDto> toListDto(Set<Relationship> relationships) {
        return relationships.stream()
                .map(RelationshipMapper::toListDto)
                .collect(Collectors.toSet());
    }

    public static RelationshipListDto toListDto(Relationship relationship) {
        return new RelationshipListDto()
                .setId(relationship.getId())
                .setCreatedAtUtc(relationship.getCreatedAtUtc())
                .setStatus(relationship.getStatus());
    }

    public static Relationship fromSaveDto(RelationshipSaveDto relationshipSaveDto) {
        return new Relationship()
                .setCreatedAtUtc(relationshipSaveDto.getCreatedAtUtc())
                .setStatus(relationshipSaveDto.getStatus());
    }
}
