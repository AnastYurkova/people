package com.ku.people.service;

import com.ku.people.dto.RelationshipDto;
import com.ku.people.dto.RelationshipListDto;
import com.ku.people.dto.RelationshipSaveDto;
import com.ku.people.entity.Relationship;
import com.ku.people.mapper.RelationshipMapper;
import com.ku.people.repository.RelationshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationshipService {

    private RelationshipRepository relationshipRepository;


    public RelationshipDto findById(Long id) {
        return relationshipRepository.findById(id);
    }

    public List<RelationshipListDto> findAll() {
        return relationshipRepository.findAll();
    }

    public RelationshipSaveDto save(RelationshipSaveDto relationshipSaveDto) {
        return relationshipRepository.save(relationshipSaveDto);
    }

    public void update(RelationshipSaveDto relationshipSaveDto) {
        relationshipRepository.update(relationshipSaveDto);
    }

    public void delete(Long id) {
        relationshipRepository.delete(id);
    }

    @Autowired
    public void setRelationshipRepository(RelationshipRepository relationshipRepository) {
        this.relationshipRepository = relationshipRepository;
    }
}
