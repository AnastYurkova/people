package com.ku.people.repository.dataJPA;

import com.ku.people.entity.Authority;
import com.ku.people.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}
