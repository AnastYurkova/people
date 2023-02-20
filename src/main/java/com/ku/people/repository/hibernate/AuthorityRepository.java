package com.ku.people.repository.hibernate;

import com.ku.people.entity.Authority;
import com.ku.people.exception.RepositoryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorityRepository {
    public static final String FIND_BY_ID_QUERY = """
          FROM Authority a
               EFT JOIN FETCH a.roles
          WHERE a.id = :id
    """;
    public static final String FIND_ALL_QUERY = "FROM Authority";
    public static final String UPDATE_QUERY = """
          UPDATE authorities SET authority_name = :authority_name WHERE id = :id
    """;

    private final SessionFactory sessionFactory;

    public AuthorityRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Authority findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(FIND_BY_ID_QUERY, Authority.class).setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            throw new RepositoryException(String.format("Failed to find authority where id = %d!", id), e);
        }
    }

    public List<Authority> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(FIND_ALL_QUERY, Authority.class)
                    .list();
        } catch (Exception e) {
            throw new RepositoryException("Failed to find all authorities: table authorities is empty!", e);
        }
    }

    public boolean save(Authority authority) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.persist(authority);
                session.getTransaction().commit();
                return true;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RepositoryException("Failed to save authority: this authority already exist", e);
            }
        }
    }

    public boolean update(Authority authority) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.createNativeQuery(UPDATE_QUERY, Authority.class)
                        .setParameter("authority_name", authority.getAuthorityName())
                        .setParameter("id", authority.getId())
                        .executeUpdate();
                session.getTransaction().commit();
                return true;
            } catch (Exception e) {
                session.getTransaction().rollback();
                String message = "Failed to update authority with id = %d. This authority is not exist!";
                throw new RepositoryException(String.format(message, authority.getId()), e);
            }
        }
    }

    public boolean delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                Authority authority = session.getReference(Authority.class, id);
                session.remove(authority);
                session.getTransaction().commit();
                return true;
            } catch (Exception e) {
                session.getTransaction().rollback();
                String message = "Failed to delete authority with id = %d. This authority is not exist!";
                throw new RepositoryException(String.format(message), e);
            }
        }
    }
}
