package com.ku.people.repository.hibernate;

import com.ku.people.entity.Authority;
import com.ku.people.entity.Role;
import com.ku.people.exception.RepositoryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class RoleRepository {
    public static final String FIND_BY_ID_QUERY = """
        FROM Role r
            LEFT JOIN FETCH r.users
            LEFT JOIN FETCH r.authorities
        WHERE r.id = :id
    """;
    public static final String FIND_ALL_QUERY = "FROM Role";
    public static final String UPDATE_QUERY = """
        UPDATE roles SET role_name = :role_name WHERE id = :id
    """;

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
                return true;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RepositoryException("Failed to save role: this role already exist", e);
            }
        }
    }

    public boolean update(Role role) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.createNativeQuery(UPDATE_QUERY, Role.class)
                        .setParameter("role_name", role.getName())
                        .setParameter("id", role.getId())
                        .executeUpdate();
                session.getTransaction().commit();
                return true;
            } catch (Exception e) {
                session.getTransaction().rollback();
                String message = "Failed to update role with id = %d. This role is not exist!";
                throw new RepositoryException(String.format(message, role.getId()), e);
            }
        }
    }

    public boolean delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                Role Role = session.getReference(Role.class, id);
                session.remove(Role);
                session.getTransaction().commit();
                return true;
            } catch (Exception e) {
                session.getTransaction().rollback();
                String message = "Failed to delete role with id = %d. This role is not exist!";
                throw new RepositoryException(String.format(message), e);
            }
        }
    }
}
