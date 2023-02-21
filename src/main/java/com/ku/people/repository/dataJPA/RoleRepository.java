package com.ku.people.repository.dataJPA;

import com.ku.people.entity.Role;
import com.ku.people.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("FROM Role r LEFT JOIN FETCH r.users LEFT JOIN FETCH r.authorities WHERE r.id = :id")
    Optional<Role> findById(Long id);
}
