//package com.ku.people.util;
//
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//import org.springframework.stereotype.Component;
//
//@Component
//public class HibernateUtil {
//    private HibernateUtil() {}
//
//    private static class SessionFactoryHolder {
//        public static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
//    }
//
//    public static SessionFactory getSessionFactory() {
//        return SessionFactoryHolder.sessionFactory;
//    }
//}