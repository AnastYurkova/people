package com.ku.people.repository.dataJPA;

import com.ku.people.entity.Relationship;
import com.ku.people.entity.Role;
import com.ku.people.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RelationshipRepository extends JpaRepository<Relationship, Long> {
    @Query("FROM Relationship r LEFT JOIN FETCH r.details WHERE r.id = :id")
    Optional<Relationship> findById(Long id);
}
