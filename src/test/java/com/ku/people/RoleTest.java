package com.ku.people;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class RoleTest {

    @Test
    void testEquals_emptyListOfUsersAndAuthoritiesInBothRoles() {
        //given
        Role role1 = new Role(1L, "name", new ArrayList<>(), new ArrayList<>());
        Role role2 = new Role(1L, "name", new ArrayList<>(), new ArrayList<>());

        //when
        boolean actual = role1.equals(role2);

        //then
        Assertions.assertTrue(actual);
    }

    @Test
    void testEquals_nullUsersInFirstRole() {
        //given
        Role role1 = new Role(1L, "name", null, new ArrayList<>());
        Role role2 = new Role(1L, "name", new ArrayList<>(), new ArrayList<>());

        //when
        boolean actual = role1.equals(role2);

        //then
        Assertions.assertFalse(actual);
    }

    @Test
    void testEquals_nullUsersInBothRoles() {
        //given
        Role role1 = new Role(1L, "name", null, new ArrayList<>());
        Role role2 = new Role(1L, "name", null, new ArrayList<>());

        //when
        boolean actual = role1.equals(role2);

        //then
        Assertions.assertTrue(actual);
    }

    @Test
    void testEquals_nullAuthoritiesInFirstRole() {
        //given
        Role role1 = new Role(1L, "name", new ArrayList<>(), null);
        Role role2 = new Role(1L, "name", new ArrayList<>(), new ArrayList<>());

        //when
        boolean actual = role1.equals(role2);

        //then
        Assertions.assertFalse(actual);
    }

    @Test
    void testEquals_nullAuthoritiesBothRoles() {
        //given
        Role role1 = new Role(1L, "name", new ArrayList<>(), null);
        Role role2 = new Role(1L, "name", new ArrayList<>(), null);

        //when
        boolean actual = role1.equals(role2);

        //then
        Assertions.assertTrue(actual);
    }

    @Test
    void testEquals_emptyListOfAuthoritiesInBothRoles() {
        //given
        List<User> users = new ArrayList<>();
        User user = new User(1L, "Nass", "123", "Yurkova", "Nastya", new ArrayList<>(), new ArrayList<>());
        users.add(user);

        Role role1 = new Role(1L, "name", users, new ArrayList<>());
        Role role2 = new Role(1L, "name", users, new ArrayList<>());

        //when
        boolean actual = role1.equals(role2);

        //then
        Assertions.assertTrue(actual);
    }

    @Test
    void testEquals_emptyListOfUsersInBothRoles() {
        //given
        List<Authority> authorities = new ArrayList<>();
        Authority authority1 = new Authority(1L, "User", new ArrayList<>());
        Authority authority2 = new Authority(2L, "Admin", new ArrayList<>());
        authorities.add(authority1);
        authorities.add(authority2);

        Role role1 = new Role(1L, "name", new ArrayList<>(), authorities);
        Role role2 = new Role(1L, "name", new ArrayList<>(), authorities);

        //when
        boolean actual = role1.equals(role2);

        //then
        Assertions.assertTrue(actual);
    }


    @Test
    void testHashCode_emptyListOfUsersAndAuthorities() {
        //given
        Role role = new Role(1L, "name", new ArrayList<>(), new ArrayList<>());
        int expected = 3374699;

        //when
        int actual = role.hashCode();

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testHashCode_nullListOfUsers() {
        //given
        Role role = new Role(1L, "name", null, new ArrayList<>());
        int expected = 3374699;

        //when
        int actual = role.hashCode();

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testHashCode_nullListOfUsersAndAuthorities() {
        //given
        Role role = new Role(1L, "name", null, null);
        int expected = 3374699;

        //when
        int actual = role.hashCode();

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testHashCode_emptyListOfAuthorities() {
        //given
        List<User> users = new ArrayList<>();
        User user = new User(1L, "Nass", "123", "Yurkova", "Nastya",  new ArrayList<>(),  new ArrayList<>());
        users.add(user);
        Role role = new Role(1L, "name", users, new ArrayList<>());
        int expected = 104615670;

        //when
        int actual = role.hashCode();

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testHashCode_emptyListOfUsers() {
        //given
        List<Authority> authorities = new ArrayList<>();
        List<Role> roles = new ArrayList<>();
        Authority authority1 = new Authority(1L, "User", roles);
        Authority authority2 = new Authority(2L, "Admin", roles);
        authorities.add(authority1);
        authorities.add(authority2);
        Role role = new Role(1L, "name", new ArrayList<>(), authorities);
        int expected = -1051881524;

        //when
        int actual = role.hashCode();

        //then
        Assertions.assertEquals(expected, actual);
    }
}