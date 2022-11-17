package com.ku.people;

import com.ku.people.entity.Detail;
import com.ku.people.entity.Relationship;
import com.ku.people.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class RelationshipTest {

    @Test
    void testEquals_emptyListOfDetailsInBothRelationships() {
        //given
        Relationship relationship1 = new Relationship(4L, LocalDate.of(2022, 11, 10), "ok", new ArrayList<>());
        Relationship relationship2 = new Relationship(4L, LocalDate.of(2022, 11, 10), "ok", new ArrayList<>());

        //when
        boolean actual = relationship1.equals(relationship2);

        //then
        Assertions.assertTrue(actual);
    }

    @Test
    void testEquals_nullDetailsInFirstRelationship() {
        //given
        Relationship relationship1 = new Relationship(4L, LocalDate.of(2022, 11, 10), "ok", null);
        Relationship relationship2 = new Relationship(4L, LocalDate.of(2022, 11, 10), "ok", new ArrayList<>());

        //when
        boolean actual = relationship1.equals(relationship2);

        //then
        Assertions.assertFalse(actual);
    }

    @Test
    void testEquals_nullDetailsInBothRelationships() {
        //given
        Relationship relationship1 = new Relationship(4L, LocalDate.of(2022, 11, 10), "ok", null);
        Relationship relationship2 = new Relationship(4L, LocalDate.of(2022, 11, 10), "ok", null);

        //when
        boolean actual = relationship1.equals(relationship2);

        //then
        Assertions.assertTrue(actual);
    }

    @Test
    void testEquals_emptyListOfDetailsInFirstRelationship() {
        //given
        List<Detail> details = new ArrayList<>();
        Detail detail1 = new Detail(4L, "hz", new User(), new Relationship());
        Detail detail2 = new Detail(5L, "hz", new User(), new Relationship());
        details.add(detail1);
        details.add(detail2);

        Relationship relationship1 = new Relationship(4L, LocalDate.of(2022, 11, 10), "ok", new ArrayList<>());
        Relationship relationship2 = new Relationship(4L, LocalDate.of(2022, 11, 10), "ok", details);

        //when
        boolean actual = relationship1.equals(relationship2);

        //then
        Assertions.assertFalse(actual);
    }

    @Test
    void testEquals_emptyListOfDetailsInSecondRelationship() {
        //given
        List<Detail> details = new ArrayList<>();
        Detail detail1 = new Detail(4L, "hz", new User(), new Relationship());
        Detail detail2 = new Detail(5L, "hz", new User(), new Relationship());
        details.add(detail1);
        details.add(detail2);

        Relationship relationship1 = new Relationship(4L, LocalDate.of(2022, 11, 10), "ok", details);
        Relationship relationship2 = new Relationship(4L, LocalDate.of(2022, 11, 10), "ok", new ArrayList<>());

        //when
        boolean actual = relationship1.equals(relationship2);

        //then
        Assertions.assertFalse(actual);
    }

    @Test
    void testHashCode_emptyListOfDetails() {
        //given
        Relationship relationship = new Relationship(4L, LocalDate.of(2022, 11, 10), "ok", new ArrayList<>());
        int expected = 128432053;

        //when
        int actual = relationship.hashCode();

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testHashCode_nullListOfDetails() {
        //given
        Relationship relationship = new Relationship(4L, LocalDate.of(2022, 11, 10), "ok", null);
        int expected = 128432053;

        //when
        int actual = relationship.hashCode();

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testHashCode_withListOfDetails() {
        //given
        List<Detail> details = new ArrayList<>();
        Detail detail1 = new Detail(4L, "hz", new User(), new Relationship());
        Detail detail2 = new Detail(5L, "hz", new User(), new Relationship());
        details.add(detail1);
        details.add(detail2);
        Relationship relationship = new Relationship(4L, LocalDate.of(2022, 11, 10), "ok", details);
        int expected = -1130848522;

        //when
        int actual = relationship.hashCode();

        //then
        Assertions.assertEquals(expected, actual);
    }

}