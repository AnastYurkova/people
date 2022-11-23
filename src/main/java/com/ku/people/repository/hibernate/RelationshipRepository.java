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
        insert into Relationship (created_at_utc, relationship_status) values (?1, ?2)
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
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Query<Relationship> query = session.createQuery(SAVE_QUERY, Relationship.class);
            query.setParameter("1", relationship.getCreatedAtUtc());
            query.setParameter("2", relationship.getStatus());
            query.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
            throw new RepositoryException("Failed to save relationship: this relationship already exist", e);
        }
        return true;
    }

    public boolean update(Relationship relationship) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(relationship);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            String message = "Failed to update relationship with id = %d. This relationship is not exist!";
            throw new RepositoryException(String.format(message, relationship.getId()), e);
        }
        return true;
    }

    public boolean delete(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Relationship relationship = session.getReference(Relationship.class, id);
            session.remove(relationship);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            String message = "Failed to delete relationship. This relationship is not exist!";
            throw new RepositoryException(String.format(message), e);
        }
        return true;
    }
}
