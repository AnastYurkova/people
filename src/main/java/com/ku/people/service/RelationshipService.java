package com.ku.people.service;


import com.ku.people.entity.Relationship;
import com.ku.people.repository.dataJPA.RelationshipRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RelationshipService {

    private final RelationshipRepository relationshipRepository;

    public RelationshipService(RelationshipRepository relationshipRepository) {
        this.relationshipRepository = relationshipRepository;
    }

    public Optional<Relationship> findById(Long id) {
        return relationshipRepository.findById(id);
    }

    public List<Relationship> findAll() {
        return relationshipRepository.findAll();
    }

    public void save(Relationship relationship) {
        relationshipRepository.save(relationship);
    }

    public void update(Relationship relationship) {
        relationshipRepository.save(relationship);
    }

    public void delete(Relationship relationship) {
        relationshipRepository.delete(relationship);
    }

}
