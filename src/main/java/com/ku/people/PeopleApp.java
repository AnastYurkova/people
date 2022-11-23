package com.ku.people;

import com.ku.people.entity.Relationship;
import com.ku.people.entity.RelationshipStatus;
import com.ku.people.entity.User;
import com.ku.people.repository.hibernate.RelationshipRepository;
import com.ku.people.repository.hibernate.UserRepository;
import com.ku.people.util.HibernateUtil;
import org.hibernate.SessionFactory;

import java.time.LocalDate;

public class PeopleApp {
    public static void main(String[] args) throws Exception {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        UserRepository userRepository = new UserRepository(sessionFactory);
////        User byId = userRepository.findById(14L);
////        System.out.println(byId);
//
////        List<User> users = userRepository.findAll();
////         System.out.println(users);
//
//        User user = new User();
////        user.setId(153L);
//        user.setUsername("Dima");
//        user.setPassword("123098");
//        user.setSurname("FFFFF");
//        user.setName("User999");
//        System.out.println(userRepository.save(user));
//        System.out.println(userRepository.delete(155L));

//        RoleRepository roleRepository = new RoleRepository(sessionFactory);
//        Role byId = roleRepository.findById(2L);
//        System.out.println(byId);
//        System.out.println(roleRepository.findAll());
//
//        Role role = new Role();
//        role.setId(5L);
//        role.setName("byk");
//        System.out.println(roleRepository.save(role));
//        System.out.println(roleRepository.update(role));
//        System.out.println(roleRepository.delete(6L));

//        AuthorityRepository authorityRepository = new AuthorityRepository(sessionFactory);
//        Authority byId = authorityRepository.findById(2L);
//        System.out.println(byId);
//        System.out.println(authorityRepository.findAll());
//
//        Authority authority = new Authority();
//        authority.setId(7L);
//        authority.setAuthorityName("byka");
//        System.out.println(authorityRepository.save(authority));
//        System.out.println(authorityRepository.update(authority));
//        System.out.println(authorityRepository.delete(7L));


//        DetailRepository detailRepository = new DetailRepository(sessionFactory);
//        Detail byId = detailRepository.findById(2L);
//        System.out.println(byId);
//        System.out.println(detailRepository.findAll());

//        Detail detail = new Detail();
//        detail.setId(7L);
//        detail.setType(RelationshipType.DAUGHTER.getValue());
//        detail.setUser(new User(56L));
//        detail.setRelationship(new Relationship(12L));
//        System.out.println(detailRepository.save(detail));
//        System.out.println(detailRepository.update(detail));
//        System.out.println(detailRepository.delete(7L));
//
        RelationshipRepository relationshipRepository = new RelationshipRepository(sessionFactory);
////        Relationship byId = relationshipRepository.findById(14L);
////        System.out.println(byId);
//
////        List<Relationship> relationships = relationshipRepository.findAll();
////         System.out.println(relationships);
//
        Relationship relationship = new Relationship();
////        relationship.setId(153L);
        relationship.setCreatedAtUtc(LocalDate.of(2033,2,10));
        relationship.setStatus(RelationshipStatus.HAPPY.getValue());
        System.out.println(relationshipRepository.save(relationship));
//        System.out.println(relationshipRepository.update(relationship));
//        System.out.println(relationshipRepository.delete(106L));


    }
}
