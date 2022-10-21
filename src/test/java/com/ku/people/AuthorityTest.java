package com.ku.people;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class AuthorityTest {

    @Test
    void testEquals_emptyListOfRolesInBothAuthorities() {
        //given
        Authority authority1 = new Authority(1L, "User", new ArrayList<>());
        Authority authority2 = new Authority(1L, "User", new ArrayList<>());

        //when
        boolean actual = authority1.equals(authority2);

        //then
        Assertions.assertTrue(actual);
    }

    @Test
    void testEquals_nullRolesInFirstAuthority() {
        //given
        Authority authority1 = new Authority(1L, "User", null);
        Authority authority2 = new Authority(1L, "User", new ArrayList<>());

        //when
        boolean actual = authority1.equals(authority2);

        //then
        Assertions.assertFalse(actual);
    }

    @Test
    void testEquals_nullRolesInBothAuthorities() {
        //given
        Authority authority1 = new Authority(1L, "User", null);
        Authority authority2 = new Authority(1L, "User", null);

        //when
        boolean actual = authority1.equals(authority2);

        //then
        Assertions.assertTrue(actual);
    }

    @Test
    void testEquals_emptyListOfRolesInFirstAuthority() {
        //given
        List<Role> roles = new ArrayList<>();
        Role role1 = new Role(1L, "name", new ArrayList<>(), new ArrayList<>());
        Role role2 = new Role(2L, "name", new ArrayList<>(), new ArrayList<>());
        roles.add(role1);
        roles.add(role2);

        Authority authority1 = new Authority(1L, "User", new ArrayList<>());
        Authority authority2 = new Authority(1L, "User", roles);

        //when
        boolean actual = authority1.equals(authority2);

        //then
        Assertions.assertFalse(actual);
    }

    @Test
    void testEquals_emptyListOfRolesInSecondAuthority() {
        //given
        List<Role> roles = new ArrayList<>();
        Role role1 = new Role(1L, "name", new ArrayList<>(), new ArrayList<>());
        Role role2 = new Role(2L, "name", new ArrayList<>(), new ArrayList<>());
        roles.add(role1);
        roles.add(role2);

        Authority authority1 = new Authority(1L, "User", roles);
        Authority authority2 = new Authority(1L, "User", new ArrayList<>());

        //when
        boolean actual = authority1.equals(authority2);

        //then
        Assertions.assertFalse(actual);
    }

    @Test
    void testHashCode_emptyListOfRoles() {
        //given
        Authority authority = new Authority(1L, "User", new ArrayList<>());
        int expected = 2646987;

        //when
        int actual = authority.hashCode();

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testHashCode_nullListOfRoles() {
        //given
        Authority authority = new Authority(1L, "User", null);
        int expected = 2646987;

        //when
        int actual = authority.hashCode();

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testHashCode_withListOfRoles() {
        //given
        List<Role> roles = new ArrayList<>();
        Role role1 = new Role(1L, "name", new ArrayList<>(), new ArrayList<>());
        Role role2 = new Role(2L, "name", new ArrayList<>(), new ArrayList<>());
        roles.add(role1);
        roles.add(role2);
        Authority authority = new Authority(1L, "User", roles);
        int expected = -1751212756;

        //when
        int actual = authority.hashCode();

        //then
        Assertions.assertEquals(expected, actual);
    }


}