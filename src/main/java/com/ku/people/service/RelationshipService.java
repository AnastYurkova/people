package com.ku.people.service;


import com.ku.people.entity.Relationship;
import com.ku.people.repository.hibernate.RelationshipRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RelationshipService {

    private final RelationshipRepository relationshipRepository;

    public RelationshipService(RelationshipRepository relationshipRepository) {
        this.relationshipRepository = relationshipRepository;
    }

    public Relationship findById(Long id) {
        return relationshipRepository.findById(id);
    }

    public List<Relationship> findAll() {
        return relationshipRepository.findAll();
    }

    public void save(Relationship relationship) {
        relationshipRepository.save(relationship);
    }

    public void update(Relationship relationship) {
        relationshipRepository.update(relationship);
    }

    public void delete(Long id) {
        relationshipRepository.delete(id);
    }

}
