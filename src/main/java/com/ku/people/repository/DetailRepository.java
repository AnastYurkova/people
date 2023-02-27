package com.ku.people.repository;

import com.ku.people.entity.Detail;
import com.ku.people.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Long> {
    @Query("FROM Detail d LEFT JOIN FETCH d.user LEFT JOIN FETCH d.relationship WHERE d.id = :id")
    Optional<Detail> findById(Long id);

//    @Query("INSERT INTO details(relationship_type, user_id, relationship_id) " +
//            "VALUES (?\\\\:\\\\:relationship_type_enum, ?, ?)")
//    Optional<Detail> save();
}
