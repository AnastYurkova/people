package com.ku.people;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class UserTest {
    @Test
    void testEquals_emptyListOfRolesAndDetailsInBothUsers() {
        //given
        User user1 = new User(1L, "Nass", "123", "Yurkova", "Nastya", new ArrayList<>(), new ArrayList<>());
        User user2 = new User(1L, "Nass", "123", "Yurkova", "Nastya", new ArrayList<>(), new ArrayList<>());
        //when
        boolean actual = user1.equals(user2);
        //then
        Assertions.assertTrue(actual);
    }

    @Test
    void testEquals_nullRolesInFirstUser() {
        //given
        User user1 = new User(1L, "Nass", "123", "Yurkova", "Nastya", null, new ArrayList<>());
        User user2 = new User(1L, "Nass", "123", "Yurkova", "Nastya", new ArrayList<>(), new ArrayList<>());
        //when
        boolean actual = user1.equals(user2);
        //then
        Assertions.assertFalse(actual);
    }

    @Test
    void testEquals_nullRolesInBothUsers() {
        //given
        User user1 = new User(1L, "Nass", "123", "Yurkova", "Nastya", null, new ArrayList<>());
        User user2 = new User(1L, "Nass", "123", "Yurkova", "Nastya", null, new ArrayList<>());
        //when
        boolean actual = user1.equals(user2);
        //then
        Assertions.assertTrue(actual);
    }

    @Test
    void testEquals_nullDetailsInFirstUser() {
        //given
        User user1 = new User(1L, "Nass", "123", "Yurkova", "Nastya", new ArrayList<>(), null);
        User user2 = new User(1L, "Nass", "123", "Yurkova", "Nastya", new ArrayList<>(), new ArrayList<>());
        //when
        boolean actual = user1.equals(user2);
        //then
        Assertions.assertFalse(actual);
    }

    @Test
    void testEquals_nullDetailsInBothUser() {
        //given
        User user1 = new User(1L, "Nass", "123", "Yurkova", "Nastya", new ArrayList<>(), null);
        User user2 = new User(1L, "Nass", "123", "Yurkova", "Nastya", new ArrayList<>(), null);
        //when
        boolean actual = user1.equals(user2);
        //then
        Assertions.assertTrue(actual);
    }

    @Test
    void testEquals_emptyListOfDetailsInSecondUser() {
        //given
        List<Role> roles = new ArrayList<>();
        Role role1 = new Role(1L, "name", new ArrayList<>(), new ArrayList<>());
        Role role2 = new Role(2L, "name", new ArrayList<>(), new ArrayList<>());
        roles.add(role1);
        roles.add(role2);

        User user1 = new User(1L, "Nass", "123", "Yurkova", "Nastya", roles, new ArrayList<>());
        User user2 = new User(1L, "Nass", "123", "Yurkova", "Nastya", roles, new ArrayList<>());

        //when
        boolean actual = user1.equals(user2);
        //then
        Assertions.assertTrue(actual);
    }

    @Test
    void testEquals_emptyListOfRolesInFirstUser() {
        //given
        List<Detail> details = new ArrayList<>();

        Detail detail1 = new Detail(4L, "hz", new User(), new Relationship());
        Detail detail2 = new Detail(4L, "hz", new User(), new Relationship() );

        details.add(detail1);
        details.add(detail2);


        User user1 = new User(1L, "Nass", "123", "Yurkova", "Nastya", new ArrayList<>(), details);
        User user2 = new User(1L, "Nass", "123", "Yurkova", "Nastya", new ArrayList<>(), details);
        //when
        boolean actual = user1.equals(user2);
        //then
        Assertions.assertTrue(actual);
    }

    @Test
    void testHashCode_emptyListOfRolesAndDetails() {
        //given
        User user = new User(1L, "Nass", "123", "Yurkova", "Nastya",  new ArrayList<>(), new ArrayList<>());
        int expected = -1615042184;

        //when
        int actual = user.hashCode();

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testHashCode_nullListOfRoles() {
        //given
        User user = new User(1L, "Nass", "123", "Yurkova", "Nastya", null, new ArrayList<>());
        int expected = -1615042184;

        //when
        int actual = user.hashCode();

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testHashCode_nullListOfRolesAndDetails() {
        //given
        User user = new User(1L, "Nass", "123", "Yurkova", "Nastya", null, null);
        int expected = -1615042184;

        //when
        int actual = user.hashCode();

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testHashCode_emptyListOfDetails() {
        List<Role> roles = new ArrayList<>();
        List<Detail> details = new ArrayList<>();

        Detail detail1 = new Detail(4L, "hz", new User(), new Relationship());
        Detail detail2 = new Detail(4L, "hz", new User(), new Relationship());

        details.add(detail1);
        details.add(detail2);

        Role role1 = new Role(1L, "name", new ArrayList<>(), new ArrayList<>());
        Role role2 = new Role(2L, "name", new ArrayList<>(), new ArrayList<>());

        roles.add(role1);
        roles.add(role2);

        User user = new User(1L, "Nass", "123", "Yurkova", "Nastya", roles,  new ArrayList<>());
        //given
        int expected = -1572344935;

        //when
        int actual = user.hashCode();

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testHashCode_emptyListOfRoles() {
        List<Role> roles = new ArrayList<>();
        List<Detail> details = new ArrayList<>();

        Detail detail1 = new Detail(4L, "hz", new User(), new Relationship());
        Detail detail2 = new Detail(4L, "hz", new User(), new Relationship());

        details.add(detail1);
        details.add(detail2);

        Role role1 = new Role(1L, "name", new ArrayList<>(), new ArrayList<>());
        Role role2 = new Role(2L, "name", new ArrayList<>(), new ArrayList<>());

        roles.add(role1);
        roles.add(role2);

        User user = new User(1L, "Nass", "123", "Yurkova", "Nastya",  new ArrayList<>(), details);
        //given
        int expected = -1572344840;

        //when
        int actual = user.hashCode();

        //then
        Assertions.assertEquals(expected, actual);
    }

}