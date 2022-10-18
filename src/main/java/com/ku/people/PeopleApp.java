package com.ku.people;

import java.time.LocalDate;

public class PeopleApp {
    public static void main(String[] args) {
        User user1 = new User(null, "Nass", "123", "Yurkova", "Nastya");
        User user2 = new User(null, "Nass", "123", "Yurkova", "Nastya");
        System.out.println(user1.equals(user2));
        System.out.println(user1.hashCode());
        System.out.println(user2.hashCode());
        System.out.println(user1);
        System.out.println(user2);

        Role role1 = new Role(1L,"i");
        Role role2 = new Role(2L,"ne i");
        System.out.println(role1.equals(role2));
        System.out.println(role1.hashCode());
        System.out.println(role2.hashCode());
        System.out.println(role1);
        System.out.println(role2);

        Authority authority1 = new Authority(1L,"User");
        Authority authority2 = new Authority(2L,"Admin");
        System.out.println(authority1.equals(authority2));
        System.out.println(authority1.hashCode());
        System.out.println(authority2.hashCode());
        System.out.println(authority1);
        System.out.println(authority2);

        Detail detail1 = new Detail(4L,"hz",1L,1L);
        Detail detail2 = new Detail(5L,"hz",2L,2L);
        System.out.println(detail1.equals(detail2));
        System.out.println(detail1.hashCode());
        System.out.println(detail2.hashCode());
        System.out.println(detail1);
        System.out.println(detail2);

        Relationship relationship1 = new Relationship(4L, LocalDate.of(2022,11,10),"ok");
        Relationship relationship2 = new Relationship(5L, LocalDate.of(2011,11,10),"ok");
        System.out.println(relationship1.equals(relationship2));
        System.out.println(relationship1.hashCode());
        System.out.println(relationship2.hashCode());
        System.out.println(relationship1);
        System.out.println(relationship2);
    }
}