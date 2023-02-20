//package com.ku.people.repository.hibernate;
//
//import com.ku.people.entity.User;
//import com.ku.people.exception.RepositoryException;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.query.Query;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public class UserRepository {
//    public static final String FIND_BY_ID_QUERY = """
//          FROM User u
//              LEFT JOIN FETCH u.roles
//              LEFT JOIN FETCH u.details
//         WHERE u.id = :id
//    """;
//    public static final String FIND_ALL_QUERY = "FROM User";
//    public static final String UPDATE_QUERY = """
//        UPDATE users SET user_name = :user_name, password = :password, surname = :surname, name = :name
//        WHERE id = :id
//    """;
//
//    private final SessionFactory sessionFactory;
//
//    @Autowired
//    public UserRepository(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    public User findById(Long id) {
//        try (Session session = sessionFactory.openSession()) {
//            Query<User> query = session.createQuery(FIND_BY_ID_QUERY, User.class);
//            query.setParameter("id", id);
//            return query.getSingleResult();
//        } catch (Exception ex) {
//            throw new RepositoryException(String.format("Failed to find user where id = %d!", id), ex);
//        }
//    }
//
//    public List<User> findAll() {
//        try (Session session = sessionFactory.openSession()) {
//            return session.createQuery(FIND_ALL_QUERY, User.class).list();
//        } catch (Exception ex) {
//            throw new RepositoryException("Failed to find all users: table users is empty!", ex);
//        }
//    }
//
//    public boolean save(User user) {
//        try (Session session = sessionFactory.openSession()) {
//            try {
//                session.beginTransaction();
//                session.persist(user);
//                session.getTransaction().commit();
//                return true;
//            } catch (Exception e) {
//                session.getTransaction().rollback();
//                throw new RepositoryException("Failed to save user: this user already exist", e);
//            }
//        }
//    }
//
//    public boolean update(User user) {
//        try (Session session = sessionFactory.openSession()) {
//            try {
//                session.beginTransaction();
//                session.createNativeQuery(UPDATE_QUERY, User.class)
//                        .setParameter("user_name", user.getUsername())
//                        .setParameter("password", user.getPassword())
//                        .setParameter("surname", user.getSurname())
//                        .setParameter("name", user.getName())
//                        .setParameter("id", user.getId())
//                        .executeUpdate();
//                session.getTransaction().commit();
//                return true;
//            } catch (Exception e) {
//                session.getTransaction().rollback();
//                String message = "Failed to update user with id = %d. This user is not exist!";
//                throw new RepositoryException(String.format(message, user.getId()), e);
//            }
//        }
//    }
//
//    public boolean delete(Long id) {
//        try (Session session = sessionFactory.openSession()) {
//            try {
//                session.beginTransaction();
//                User user = session.getReference(User.class, id);
//                session.remove(user);
//                session.getTransaction().commit();
//                return true;
//            } catch (Exception e) {
//                session.getTransaction().rollback();
//                String message = "Failed to delete user  with id = %d. This user is not exist!";
//                throw new RepositoryException(String.format(message), e);
//            }
//        }
//    }
//}
