package com.ku.people.repository;

import com.ku.people.entity.Detail;
import com.ku.people.entity.Relationship;
import com.ku.people.exception.RepositoryException;
import com.ku.people.entity.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DetailRepository implements Repository {
    public static final String FIND_BY_ID_QUERY = """
        SELECT
            d.id, d.relationship_type, u.id user_id, u.user_name, u.password,u.surname,
            u.name, r.id relationship_id, r.created_at_utc, r.relationship_status
        FROM details d
           LEFT JOIN users u ON u.id = d.user_id
           LEFT JOIN relationships r  ON r.id = d.relationship_id
        WHERE d.id = ?
    """;
    public static final String FIND_ALL_QUERY = """
        SELECT
            d.id, d.relationship_type, u.id user_id, u.user_name, u.password,u.surname,
            u.name, r.id relationship_id, r.created_at_utc, r.relationship_status
        FROM details d
           LEFT JOIN users u ON u.id = d.user_id
           LEFT JOIN relationships r  ON r.id = d.relationship_id
    """;
    public static final String SAVE_QUERY = """
        INSERT INTO details(relationship_type, user_id, relationship_id) VALUES (?::relationship_type_enum, ?, ?)
    """;
    public static final String UPDATE_QUERY = """
        UPDATE details SET relationship_type = ?::relationship_type_enum, user_id = ?, relationship_id = ? WHERE id = ?
    """;
    public static final String DELETE_QUERY = "DELETE FROM details WHERE id = ?";

    private final DataSource dataSource;

    public DetailRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public Detail findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY)
        ) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                Detail detail = new Detail();
                while (resultSet.next()) {
                    fillDetail(detail, resultSet);
                    detail.setUser(buildUser(resultSet));
                    detail.setRelationship(buildRelationship(resultSet));
                }
                return detail;
            }
        } catch (Exception ex) {
            throw new RepositoryException(String.format("Failed to find detail where id = %d!",id), ex);
        }
    }

    private void fillDetail(Detail detail, ResultSet resultSet) throws Exception {
        detail.setId(resultSet.getLong(ID_COLUMN));
        detail.setType(resultSet.getString(RELATIONSHIP_TYPE_COLUMN));
    }

    private User buildUser(ResultSet resultSet) throws Exception {
        return new User(resultSet.getLong(USER_ID_COLUMN),
                resultSet.getString(USER_NAME_COLUMN),
                resultSet.getString(PASSWORD_COLUMN),
                resultSet.getString(SURNAME_COLUMN),
                resultSet.getString(NAME_COLUMN));
    }

    private Relationship buildRelationship(ResultSet resultSet) throws Exception {
        return new Relationship(resultSet.getLong(RELATIONSHIP_ID_COLUMN),
                resultSet.getDate(CREATED_AT_UTC_COLUMN).toLocalDate(),
                resultSet.getString(RELATIONSHIP_STATUS_COLUMN));
    }

    public List<Detail> findAll() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL_QUERY);
        ) {
            List<Detail> details = new ArrayList<>();
            while (resultSet.next()) {
                Detail detail = new Detail();
                fillDetail(detail, resultSet);
                detail.setUser(buildUser(resultSet));
                detail.setRelationship(buildRelationship(resultSet));
                details.add(detail);
            }
            return details;
        } catch (Exception ex) {
            throw new RepositoryException("Failed to find all details: table details is empty", ex);
        }
    }

    public boolean save(Detail detail) throws Exception {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_QUERY)
        ) {
            try {
                buildQuery(preparedStatement, detail);
                preparedStatement.executeUpdate();
            } catch (Exception ex) {
                throw new RepositoryException("Failed to save detail: this detail already exist", ex);
            }
        }
        return true;
    }

    private void buildQuery(PreparedStatement preparedStatement, Detail detail) throws Exception {
        preparedStatement.setString(1, detail.getType());
        Optional.ofNullable(detail.getUser())
                .ifPresent(user -> setId(preparedStatement, user.getId()));
        Optional.ofNullable(detail.getRelationship())
                .ifPresent(relationship -> setId(preparedStatement, relationship.getId()));
    }

    private static void setId(PreparedStatement preparedStatement, Long id) {
        try {
            preparedStatement.setLong(2,id);
        } catch (SQLException e) {
            throw new RepositoryException("Failed to save a detail.");
        }
    }


    public boolean update(Detail detail) throws Exception {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
        ) {
            try {
                buildQuery(preparedStatement,detail);
                preparedStatement.setLong(4, detail.getId());
                preparedStatement.executeUpdate();
            } catch (Exception ex) {
                String message = "Failed to update detail with id=%d. This detail is not exist!";
                throw new RepositoryException(String.format(message, detail.getId()), ex);
            }
        }
        return true;
    }

    public boolean delete(Long id) throws Exception {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
        ) {
            try {
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
            } catch (Exception ex) {
                String message = "Failed to delete detail with id=%d. This detail is not exist!";
                throw new RepositoryException(String.format(message, id), ex);
            }
        }
        return true;
    }
}
