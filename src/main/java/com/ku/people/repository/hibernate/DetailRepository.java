package com.ku.people.repository.hibernate;

import com.ku.people.entity.Detail;
import com.ku.people.exception.RepositoryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DetailRepository {
    public static final String FIND_BY_ID_QUERY = """
          FROM Detail d
          WHERE d.id = :id
    """;
    public static final String FIND_ALL_QUERY = "FROM Detail";
    public static final String SAVE_QUERY = """
          INSERT INTO details(relationship_type, user_id, relationship_id)
          VALUES (?\\:\\:relationship_type_enum, ?, ?)
    """;
    public static final String UPDATE_QUERY = """
         UPDATE details SET relationship_type = ?\\:\\:relationship_type_enum, user_id = ?, relationship_id = ?
         WHERE id = ?
    """;

    private final SessionFactory sessionFactory;

    public DetailRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Detail findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Detail> query = session.createQuery(FIND_BY_ID_QUERY, Detail.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (Exception ex) {
            throw new RepositoryException(String.format("Failed to find detail where id = %d!", id), ex);
        }
    }

    public List<Detail> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(FIND_ALL_QUERY, Detail.class).list();
        } catch (Exception ex) {
            throw new RepositoryException("Failed to find all details: table details is empty!", ex);
        }
    }

    public boolean save(Detail detail) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.createNativeQuery(SAVE_QUERY, Detail.class)
                        .setParameter(1, detail.getType())
                        .setParameter(2, detail.getUser().getId())
                        .setParameter(3, detail.getRelationship().getId())
                        .executeUpdate();
                session.getTransaction().commit();
                return true;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RepositoryException("Failed to save detail: this detail already exist", e);
            }
        }
    }

    public boolean update(Detail detail) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.createNativeQuery(UPDATE_QUERY, Detail.class)
                        .setParameter(1, detail.getType())
                        .setParameter(2, detail.getUser().getId())
                        .setParameter(3, detail.getRelationship().getId())
                        .setParameter(4, detail.getId())
                        .executeUpdate();
                session.getTransaction().commit();
                return true;
            } catch (Exception e) {
                session.getTransaction().rollback();
                String message = "Failed to update detail with id = %d. This detail is not exist!";
                throw new RepositoryException(String.format(message, detail.getId()), e);
            }
        }
    }

    public boolean delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                Detail Detail = session.getReference(Detail.class, id);
                session.remove(Detail);
                session.getTransaction().commit();
                return true;
            } catch (Exception e) {
                session.getTransaction().rollback();
                String message = "Failed to delete detail with id = %d. This Detail is not exist!";
                throw new RepositoryException(String.format(message), e);
            }
        }
    }
}
