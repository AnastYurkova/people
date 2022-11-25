package com.ku.people.repository.hibernate;

import com.ku.people.entity.Authority;
import com.ku.people.exception.RepositoryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class AuthorityRepository {
    public static final String FIND_BY_ID_QUERY = """
        from Authority a
            left join fetch a.roles
        where a.id = :id
    """;
    public static final String FIND_ALL_QUERY = "from Authority";
    private final SessionFactory sessionFactory;

    public AuthorityRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Authority findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Authority> query = session.createQuery(FIND_BY_ID_QUERY, Authority.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (Exception ex) {
            throw new RepositoryException(String.format("Failed to find authority where id = %d!", id), ex);
        }
    }

    public List<Authority> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(FIND_ALL_QUERY, Authority.class).list();
        } catch (Exception ex) {
            throw new RepositoryException("Failed to find all authorities: table authorities is empty!", ex);
        }
    }

    public boolean save(Authority authority) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.persist(authority);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RepositoryException("Failed to save authority: this authority already exist", e);
            }
        }
        return true;
    }

    public boolean update(Authority authority) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.merge(authority);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                String message = "Failed to update authority with id = %d. This authority is not exist!";
                throw new RepositoryException(String.format(message, authority.getId()), e);
            }
        }
        return true;
    }

    public boolean delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                Authority Authority = session.getReference(Authority.class, id);
                session.remove(Authority);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                String message = "Failed to delete authority. This authority is not exist!";
                throw new RepositoryException(String.format(message), e);
            }
        }
        return true;
    }
}
