package com.ku.people;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Repository{
    public static final String FIND_BY_ID_QUERY = """
        SELECT u.id, u.user_name, u.password, u.surname, u.name, r.id role_id, r.role_name, d.id detail_id
        FROM users u
            LEFT JOIN user_role_links url  ON u.id = url.user_id
            LEFT JOIN roles r ON url.role_id = r.id
            LEFT JOIN details d ON d.user_id = u.id 
        WHERE u.id = ?
    """;
    public static final String FIND_ALL_QUERY = "SELECT * FROM users";
    public static final String SAVE_QUERY = """
        INSERT INTO users(user_name, password, surname, name) VALUES (?, ?, ?, ?)
    """;
    public static final String UPDATE_QUERY = """
        UPDATE users SET user_name = ?, password = ?, surname = ?, name = ? WHERE id = ?
    """;
    public static final String DELETE_DETAIL_QUERY = "DELETE FROM details WHERE user_id = ?";
    public static final String DELETE_USER_ROLE_LINKS_QUERY = "DELETE FROM user_role_links WHERE user_id = ?";
    public static final String DELETE_QUERY = "DELETE FROM users WHERE id = ?";
    private final DataSource dataSource;

    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY)
        ) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                User user = new User();
                while (resultSet.next()) {
                    List<Role> roles = new ArrayList<>();
                    List<Detail> details = new ArrayList<>();
                    fillUser(user, resultSet);
                    do {
                        if (!roles.contains(buildRole(resultSet))) {
                            roles.add(buildRole(resultSet));
                        }
                        if (!details.contains(buildDetails(resultSet))) {
                            details.add(buildDetails(resultSet));
                        }
                    } while (resultSet.next());
                    user.setRoles(roles);
                    user.setDetails(details);
                }
                return user;
            }
        } catch (Exception ex) {
            throw new UserException("Failed to find user", ex);
        }
    }

    private void fillUser(User user, ResultSet resultSet) throws Exception {
        user.setId(resultSet.getLong(ID_COLUMN));
        user.setUsername(resultSet.getString(USER_NAME_COLUMN));
        user.setPassword(resultSet.getString(PASSWORD_COLUMN));
        user.setSurname(resultSet.getString(SURNAME_COLUMN));
        user.setName(resultSet.getString(NAME_COLUMN));
    }

    private Role buildRole(ResultSet resultSet) throws Exception {
        return new Role(resultSet.getLong(ROLE_ID_COLUMN),
                resultSet.getString(ROLE_NAME_COLUMN));
    }

    private Detail buildDetails(ResultSet resultSet) throws Exception {
        return new Detail(resultSet.getLong(DETAIL_ID_COLUMN));
    }

    public List<User> findAll() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL_QUERY);
        ) {
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User();
                fillUser(user, resultSet);
                users.add(user);
            }
            return users;
        } catch (Exception ex) {
            throw new UserException("Failed to find all users", ex);
        }
    }

    public void save(User user) throws Exception {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_QUERY)
        ) {
            try {
                buildQuery(preparedStatement, user);
                preparedStatement.executeUpdate();
            } catch (Exception ex) {
                throw new UserException("Failed to save user", ex);
            }
        }
    }

    private void buildQuery(PreparedStatement preparedStatement, User user) throws Exception {
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getSurname());
        preparedStatement.setString(4, user.getName());
    }

    public void update(User user) throws Exception {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
        ) {
            try {
                buildQuery(preparedStatement, user);
                preparedStatement.setLong(5, user.getId());
                preparedStatement.executeUpdate();
            } catch (Exception ex) {
                throw new UserException("Failed to delete user", ex);
            }
        }
    }

    public void delete(Long id) throws Exception {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
        ) {
            try {
                connection.setAutoCommit(false);
                deleteUserRoleLinks(connection, id);
                deleteDetails(connection, id);
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (Exception ex) {
                connection.rollback();
                throw new UserException("Failed to delete user", ex);
            } finally {
                connection.setAutoCommit(true);
            }
        }
    }

    private void deleteUserRoleLinks    (Connection connection, Long id) throws Exception {
        try (PreparedStatement preparedStatementForRoles = connection.prepareStatement(DELETE_USER_ROLE_LINKS_QUERY)
        ) {
            preparedStatementForRoles.setLong(1, id);
            preparedStatementForRoles.executeUpdate();
        }
    }

    private void deleteDetails(Connection connection, Long id) throws Exception {
        try (PreparedStatement preparedStatementForDetails = connection.prepareStatement(DELETE_DETAIL_QUERY)
        ) {
            preparedStatementForDetails.setLong(1, id);
            preparedStatementForDetails.executeUpdate();
        }
    }
}

