package com.ku.people.repository.hibernate;

import com.ku.people.entity.User;
import com.ku.people.exception.RepositoryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserRepository {
    public static final String FIND_BY_ID_QUERY = """
        from User u
            left join fetch u.roles
            left join fetch u.details
        where u.id = :id
    """;
    public static final String FIND_ALL_QUERY = "from User";
    private final SessionFactory sessionFactory;

    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public User findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery(FIND_BY_ID_QUERY, User.class);
            query.setParameter("id",id);
            return query.getSingleResult();
        } catch (Exception ex) {
            throw new RepositoryException(String.format("Failed to find user where id = %d!", id), ex);
        }
    }

    public List<User> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return  session.createQuery(FIND_ALL_QUERY, User.class).list();
        } catch (Exception ex) {
            throw new RepositoryException("Failed to find all users: table users is empty!", ex);
        }
    }

    public boolean save(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException("Failed to save user: this user already exist", e);
        }
        return true;
    }

    public boolean update(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            String message = "Failed to update user with id = %d. This user is not exist!";
            throw new RepositoryException(String.format(message, user.getId()), e);
        }
        return true;
    }

    public boolean delete(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = session.getReference(User.class, id);
            session.remove(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            String message = "Failed to delete user. This user is not exist!";
            throw new RepositoryException(String.format(message), e);
        }
        return true;
    }
}
