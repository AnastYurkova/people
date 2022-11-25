package com.ku.people.repository.hibernate;

import com.ku.people.entity.Role;
import com.ku.people.exception.RepositoryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class RoleRepository {
    public static final String FIND_BY_ID_QUERY = """
        from Role r
            left join fetch r.users
            left join fetch r.authorities
        where r.id = :id
    """;
    public static final String FIND_ALL_QUERY = "from Role";
    private final SessionFactory sessionFactory;

    public RoleRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Role findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Role> query = session.createQuery(FIND_BY_ID_QUERY, Role.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (Exception ex) {
            throw new RepositoryException(String.format("Failed to find Role where id = %d!", id), ex);
        }
    }

    public List<Role> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(FIND_ALL_QUERY, Role.class).list();
        } catch (Exception ex) {
            throw new RepositoryException("Failed to find all roles: table roles is empty!", ex);
        }
    }

    public boolean save(Role role) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.persist(role);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RepositoryException("Failed to save role: this role already exist", e);
            }
        }
        return true;
    }

    public boolean update(Role role) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.merge(role);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                String message = "Failed to update role with id = %d. This role is not exist!";
                throw new RepositoryException(String.format(message, role.getId()), e);
            }
        }
        return true;
    }

    public boolean delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                Role Role = session.getReference(Role.class, id);
                session.remove(Role);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                String message = "Failed to delete role. This role is not exist!";
                throw new RepositoryException(String.format(message), e);
            }
        }
        return true;
    }
}
