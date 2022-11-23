package com.ku.people.repository.hibernate;

import com.ku.people.entity.Detail;
import com.ku.people.exception.RepositoryException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class DetailRepository {
    public static final String FIND_BY_ID_QUERY = """
        from Detail d
        where d.id = :id
    """;
    public static final String FIND_ALL_QUERY = "from Detail";
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
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(detail);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RepositoryException("Failed to save detail: this detail already exist", e);
        }
        return true;
    }

    public boolean update(Detail detail) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(detail);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            String message = "Failed to update detail with id = %d. This detail is not exist!";
            throw new RepositoryException(String.format(message, detail.getId()), e);
        }
        return true;
    }

    public boolean delete(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Detail Detail = session.getReference(Detail.class, id);
            session.remove(Detail);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            String message = "Failed to delete detail. This Detail is not exist!";
            throw new RepositoryException(String.format(message), e);
        }
        return true;
    }
}
