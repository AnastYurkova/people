package com.ku.people.service;

import com.ku.people.dto.RelationshipDto;
import com.ku.people.dto.RelationshipListDto;
import com.ku.people.dto.RelationshipSaveDto;
import com.ku.people.entity.Relationship;
import com.ku.people.mapper.RelationshipMapper;
import com.ku.people.repository.RelationshipRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RelationshipService {

    private RelationshipRepository relationshipRepository;


    public RelationshipDto findById(Long id) {
        Relationship relationship = relationshipRepository.findById(id).get();
        return RelationshipMapper.toDto(relationship);
    }

    public List<RelationshipListDto> findAll() {
        List<Relationship> relationships = relationshipRepository.findAll();
        return RelationshipMapper.toListDto(relationships);
    }

    public Relationship save(RelationshipSaveDto relationshipSaveDto) {
        Relationship relationship = RelationshipMapper.fromSaveDto(relationshipSaveDto);
        return relationshipRepository.save(relationship);
    }

}
