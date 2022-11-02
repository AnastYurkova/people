package com.ku.people;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeopleApp {
    public static void main(String[] args) throws SQLException {
        UserRepository userRepository = new UserRepository(new DBWorwer());

//        System.out.println(userRepository.findById(113L));

//        System.out.println(userRepository.findAll());

//        List<Role> roles = new ArrayList<>() {{
//            add(new Role(1L));
//        }};
//        List<Detail> details = new ArrayList<>() {{
//            add(new Detail(RelationshipType.DAUGHTER.getRelationshipType(), new Relationship(1L)));
//
//        }};
//        User user = new User();
//        user.setId(141L);
//        user.setUsername("Petya");
//        user.setPassword("123455");
//        user.setSurname("Pupkin");
//        user.setName("User228");
//        user.setRoles(roles);
//        user.setDetails(details);

//        System.out.println(userRepository.add(user));

//        System.out.println(userRepository.update(user));

//        System.out.println(userRepository.delete(23L));

    }
}