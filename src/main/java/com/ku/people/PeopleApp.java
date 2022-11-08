package com.ku.people;

import org.postgresql.ds.PGSimpleDataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeopleApp {
    public static void main(String[] args) throws SQLException, UserException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
            dataSource.setURL("jdbc:postgresql://localhost:5432/postgres");
            dataSource.setUser("postgres");
            dataSource.setPassword("postgres");
        UserRepository userRepository = new UserRepository(dataSource);

        System.out.println(userRepository.findById(14L));

//        System.out.println(userRepository.findAll());

//        List<Role> roles = new ArrayList<>() {{
//            add(new Role(1L));
//        }};
//        List<Detail> details = new ArrayList<>() {{
//            add(new Detail(RelationshipType.WIFE.getValue(), new Relationship(47L)));
//
//        }};
//        User user = new User();
//        user.setId(150L);
//        user.setUsername("Anna");
//        user.setPassword("98765");
//        user.setSurname("Ytkina");
//        user.setName("User786");
//        user.setRoles(roles);
//        user.setDetails(details);

//        System.out.println(userRepository.save(user));

//        System.out.println(userRepository.update(user));

//        System.out.println(userRepository.delete(148L));

    }

}