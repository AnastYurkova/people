package com.ku.people.repository.dataJPA;

import com.ku.people.entity.Detail;
import com.ku.people.entity.Relationship;
import com.ku.people.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Long> {
    @Query("FROM Detail d WHERE d.id = :id")
    Optional<Detail> findById(Long id);
}
