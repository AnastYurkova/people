package com.ku.people.repository;

import com.ku.people.entity.Relationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RelationshipRepository extends JpaRepository<Relationship, Long> {
    @Query("FROM Relationship r LEFT JOIN FETCH r.details WHERE r.id = :id")
    Optional<Relationship> findById(Long id);

//    @Query("INSERT INTO relationships(created_at_utc, relationship_status) " +
//            "VALUES(?, ?\\\\:\\\\:relationship_status_enum))")
//    Optional<Relationship> save();
}
