package com.ku.people;

import org.postgresql.ds.PGSimpleDataSource;

import java.sql.SQLException;

public class PeopleApp {
    public static void main(String[] args) throws SQLException, UserException {
        DataSource dataSource = new DataSource(new PGSimpleDataSource());
        UserRepository userRepository = new UserRepository(dataSource);

        System.out.println(userRepository.findById(14L));

//        System.out.println(userRepository.findAll());

//        List<Role> roles = new ArrayList<>() {{
//            add(new Role(2L));
//        }};
//        List<Detail> details = new ArrayList<>() {{
//            add(new Detail(RelationshipType.DAUGHTER.getRelationshipType(), new Relationship(40L)));
//
//        }};
//        User user = new User();
//        user.setId(144L);
//        user.setUsername("Alesya");
//        user.setPassword("452089");
//        user.setSurname("Navarko");
//        user.setName("User298");
//        user.setRoles(roles);
//        user.setDetails(details);
//
//        System.out.println(userRepository.add(user));

//        System.out.println(userRepository.update(user));

//        System.out.println(userRepository.delete(141L));

    }
}