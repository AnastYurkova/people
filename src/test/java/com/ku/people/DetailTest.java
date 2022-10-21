package com.ku.people;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class DetailTest {

    @Test
    void testEquals_emptyUsersAndRelationshipInBothDetails() {
        //given
        Detail detail1 = new Detail(4L, "hz", new User(), new Relationship());
        Detail detail2 = new Detail(4L, "hz", new User(), new Relationship());

        //when
        boolean actual = detail1.equals(detail2);

        //then
        Assertions.assertTrue(actual);
    }

    @Test
    void testEquals_nullUsersInFirstDetail() {
        //given
        Detail detail1 = new Detail(4L, "hz", null, new Relationship());
        Detail detail2 = new Detail(4L, "hz", new User(), new Relationship());

        //when
        boolean actual = detail1.equals(detail2);

        //then
        Assertions.assertFalse(actual);
    }

    @Test
    void testEquals_nullUsersInBothDetails() {
        //given
        Detail detail1 = new Detail(4L, "hz", null, new Relationship());
        Detail detail2 = new Detail(4L, "hz", null, new Relationship());

        //when
        boolean actual = detail1.equals(detail2);

        //then
        Assertions.assertTrue(actual);
    }

    @Test
    void testEquals_nullRelationshipInFirstDetail() {
        //given
        Detail detail1 = new Detail(4L, "hz", new User(), null);
        Detail detail2 = new Detail(4L, "hz", new User(), new Relationship());

        //when
        boolean actual = detail1.equals(detail2);

        //then
        Assertions.assertFalse(actual);
    }

    @Test
    void testEquals_nullRelationshipsInBothDetail() {
        //given
        Detail detail1 = new Detail(4L, "hz", new User(), null);
        Detail detail2 = new Detail(4L, "hz", new User(), null);

        //when
        boolean actual = detail1.equals(detail2);

        //then
        Assertions.assertTrue(actual);
    }

    @Test
    void testEquals_emptyRelationshipsInBothDetails() {
        //given
        User user = new User(1L, "Nass", "123", "Yurkova", "Nastya", new ArrayList<>(), new ArrayList<>());

        Detail detail1 = new Detail(4L, "hz", user, new Relationship());
        Detail detail2 = new Detail(4L, "hz", user, new Relationship());

        //when
        boolean actual = detail1.equals(detail2);

        //then
        Assertions.assertTrue(actual);
    }

    @Test
    void testEquals_emptyUsersInBothDetails() {
        //given
        Relationship relationship = new Relationship(4L, LocalDate.of(2022, 11, 10), "ok", new ArrayList<>());

        Detail detail1 = new Detail(4L, "hz", new User(), relationship);
        Detail detail2 = new Detail(4L, "hz", new User(), relationship);

        //when
        boolean actual = detail1.equals(detail2);

        //then
        Assertions.assertTrue(actual);
    }

    @Test
    void testHashCode_emptyUsersAndRelationship() {
        //given
        Detail detail = new Detail(4L, "hz", new User(), new Relationship());
        int expected = 891791663;

        //when
        int actual = detail.hashCode();

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testHashCode_nullUsers() {
        //given
        Detail detail = new Detail(4L, "hz", null, new Relationship());
        int expected = 4287982;

        //when
        int actual = detail.hashCode();

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testHashCode_nullUsersAndRelationships() {
        //given
        Detail detail = new Detail(4L, "hz", null, null);
        int expected = 4258191;

        //when
        int actual = detail.hashCode();

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testHashCode_emptyRelationships() {
        //given
        User user = new User(1L, "Nass", "123", "Yurkova", "Nastya", new ArrayList<>(), new ArrayList<>());

        Detail detail = new Detail(4L, "hz", user, new Relationship());
        int expected = 622281176;

        //when
        int actual = detail.hashCode();

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testHashCode_emptyListOfRoles() {
        //given
        Relationship relationship = new Relationship(4L, LocalDate.of(2022, 11, 10), "ok", new ArrayList<>());

        Detail detail = new Detail(4L, "hz", new User(), relationship);
        int expected = 1020193925;

        //when
        int actual = detail.hashCode();

        //then
        Assertions.assertEquals(expected, actual);
    }
}