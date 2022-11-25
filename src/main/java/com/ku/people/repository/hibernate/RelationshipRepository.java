package com.ku.people.repository.hibernate;

import com.ku.people.entity.Authority;
import com.ku.people.entity.Relationship;
import com.ku.people.exception.RepositoryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class RelationshipRepository {
    public static final String FIND_BY_ID_QUERY = """
        from Relationship r
            left join fetch r.details
        where r.id = :id
    """;
    public static final String FIND_ALL_QUERY = "from Relationship";
    public static final String SAVE_QUERY = """
        insert into relationships(created_at_utc, relationship_status)
        values(?, ?\\:\\:relationship_status_enum)
    """;
    public static final String UPDATE_QUERY = """
        update relationships set created_at_utc = ?, relationship_status = ?\\:\\:relationship_status_enum
        where id = ?
    """;
    private final SessionFactory sessionFactory;

    public RelationshipRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Relationship findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Query<Relationship> query = session.createQuery(FIND_BY_ID_QUERY, Relationship.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (Exception ex) {
            throw new RepositoryException(String.format("Failed to find relationship where id = %d!", id), ex);
        }
    }

    public List<Relationship> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(FIND_ALL_QUERY, Relationship.class).list();
        } catch (Exception ex) {
            throw new RepositoryException("Failed to find all relationships: table relationships is empty!", ex);
        }
    }

    public boolean save(Relationship relationship) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.createNativeQuery(SAVE_QUERY, Relationship.class)
                        .setParameter(1, relationship.getCreatedAtUtc())
                        .setParameter(2, relationship.getStatus().getValue())
                        .executeUpdate();
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RepositoryException("Failed to save relationship: this relationship already exist", e);
            }
        }
        return true;
    }

    public boolean update(Relationship relationship) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.createNativeQuery(UPDATE_QUERY, Relationship.class)
                        .setParameter(1, relationship.getCreatedAtUtc())
                        .setParameter(2, relationship.getStatus().getValue())
                        .setParameter(3, relationship.getId())
                        .executeUpdate();
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new RepositoryException("Failed to save relationship: this relationship already exist", e);
            }
        }
        return true;
    }

    public boolean delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                Relationship relationship = session.getReference(Relationship.class, id);
                session.remove(relationship);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                String message = "Failed to delete relationship. This relationship is not exist!";
                throw new RepositoryException(String.format(message), e);
            }
        }
        return true;
    }
}
