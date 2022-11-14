package com.ku.people.Repository;

import com.ku.people.Detail;
import com.ku.people.Relationship;
import com.ku.people.Exception.RepositoryException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RelationshipRepository implements Repository {
    public static final String FIND_BY_ID_QUERY = """
        SELECT r.id, r.created_at_utc, r.relationship_status, d.id detail_id, d.relationship_type
        FROM relationships r
            LEFT JOIN details d ON r.id = d.relationship_id
        WHERE r.id = ?
    """;
    public static final String FIND_ALL_QUERY = "SELECT * FROM relationships";
    public static final String SAVE_QUERY = """
        INSERT INTO relationships(created_at_utc, relationship_status) VALUES (?, ?::relationship_status_enum)
    """;
    public static final String UPDATE_QUERY = """
        UPDATE relationships SET created_at_utc = ?, relationship_status = ?::relationship_status_enum WHERE id = ?
    """;
    public static final String DELETE_DETAIL_QUERY = "DELETE FROM details WHERE relationship_id = ?";
    public static final String DELETE_QUERY = "DELETE FROM relationships WHERE id = ?";
    private final DataSource dataSource;

    public RelationshipRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Relationship findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY)
        ) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Relationship relationship = new Relationship();
                while (resultSet.next()) {
                    List<Detail> details = new ArrayList<>();
                    fillRelationship(relationship, resultSet);
                    do {
                        if (!details.contains(buildDetails(resultSet))) {
                            details.add(buildDetails(resultSet));
                        }
                    } while (resultSet.next());
                    relationship.setDetails(details);
                }
                return relationship;
            }
        }catch (Exception ex) {
            throw new RepositoryException(String.format("Failed to find relationship where id == %d!",id), ex);
        }
    }

    private void fillRelationship(Relationship relationship, ResultSet resultSet) throws Exception {
        relationship.setId(resultSet.getLong(ID_COLUMN));
        relationship.setCreatedAtUtc(resultSet.getDate(CREATED_AT_UTC_COLUMN).toLocalDate());
        relationship.setStatus(resultSet.getString(RELATIONSHIP_STATUS_COLUMN));
    }

    private Detail buildDetails(ResultSet resultSet) throws Exception {
        return new Detail(resultSet.getLong(DETAIL_ID_COLUMN));
    }

    public List<Relationship> findAll() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL_QUERY);
        ) {
            List<Relationship> relationships = new ArrayList<>();
            while (resultSet.next()) {
                Relationship relationship = new Relationship();
                fillRelationship(relationship, resultSet);
                relationships.add(relationship);
            }
            return relationships;
        } catch (Exception ex) {
            throw new RepositoryException("Failed to find all relationships:table relationships is empty", ex);
        }
    }

    public boolean save(Relationship relationship) throws Exception {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_QUERY)
        ) {
            try {
                buildQuery(preparedStatement, relationship);
                preparedStatement.executeUpdate();
            } catch (Exception ex) {
                throw new RepositoryException("Failed to save relationship: this relationship already exist", ex);
            }
        }
        return true;
    }
    private void buildQuery(PreparedStatement preparedStatement, Relationship relationship) throws Exception {
        preparedStatement.setDate(1, Date.valueOf(relationship.getCreatedAtUtc()));
        preparedStatement.setString(2, relationship.getStatus());
    }

    public boolean update(Relationship relationship) throws Exception {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
        ) {
            try {
                buildQuery(preparedStatement,relationship);
                preparedStatement.setLong(3, relationship.getId());
                preparedStatement.executeUpdate();
            } catch (Exception ex) {
                throw new RepositoryException(String.format("Failed to update relationship with id=%d. This relationship is not exist!", relationship.getId()), ex);

            }
        }
        return true;
    }

    public boolean delete(Long id) throws Exception {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
        ) {
            try {
                connection.setAutoCommit(false);
                deleteDetails(connection, id);
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (Exception ex) {
                connection.rollback();
                throw new RepositoryException(String.format("Failed to delete relationship with id=%d. This relationship is not exist!", id), ex);
            } finally {
                connection.setAutoCommit(true);
            }
        }
        return true;
    }


    private void deleteDetails(Connection connection, Long id) throws Exception {
        try (PreparedStatement preparedStatementForDetails = connection.prepareStatement(DELETE_DETAIL_QUERY)
        ) {
            preparedStatementForDetails.setLong(1, id);
            preparedStatementForDetails.executeUpdate();
        }
    }
}
