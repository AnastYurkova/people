package com.ku.people;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    public static final String ID_COLUMN = "id";
    public static final String USER_NAME_COLUMN = "user_name";
    public static final String PASSWORD_COLUMN = "password";
    public static final String SURNAME_COLUMN = "surname";
    public static final String NAME_COLUMN = "name";
    public static final String ROLE_ID_COLUMN = "role_id";
    public static final String ROLE_NAME_COLUMN = "role_name";
    public static final String DETAILS_ID_COLUMN = "detail_id";

    public static final String QUERY_FIND_BY_ID = """
                SELECT u.id, u.user_name, u.password, u.surname, u.name, r.id role_id, r.role_name, d.id detail_id
                FROM users u
                    LEFT JOIN user_role_links url  ON u.id = url.user_id
                    LEFT JOIN roles r ON url.role_id = r.id
                    LEFT JOIN details d ON d.user_id = u.id 
                WHERE u.id = ?
            """;
    public static final String QUERY_FIND_ALL = "SELECT * FROM users";
    public static final String QUERY_SAVE = "INSERT INTO users(user_name, password, surname, name) VALUES (?, ?, ?, ?)";
    public static final String QUERY_UPDATE = "UPDATE users SET user_name = ?, password = ?, surname = ?, name = ? WHERE id = ?";
    public static final String QUERY_DELETE_DETAILS = "DELETE FROM details WHERE user_id = ? ";
    public static final String QUERY_DELETE_USER_ROLE_LINKS = "DELETE FROM user_role_links WHERE user_id = ? ";
    public static final String QUERY_DELETE = "DELETE FROM users WHERE id = ? ";

    private final DataSource dataSource;

    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User findById(Long id) {
        User user = new User();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_FIND_BY_ID)
        ) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    fillUser(user, resultSet);
                }
            }
        } catch (Exception ex) {
            throw new UserException("Failed to find user", ex);
        }
        return user;
    }

    private void fillUser(User user, ResultSet resultSet) {
        try {
            List<Role> roles = new ArrayList<>();
            List<Detail> details = new ArrayList<>();
            user.setId(resultSet.getLong(ID_COLUMN));
            user.setUsername(resultSet.getString(USER_NAME_COLUMN));
            user.setPassword(resultSet.getString(PASSWORD_COLUMN));
            user.setSurname(resultSet.getString(SURNAME_COLUMN));
            user.setName(resultSet.getString(NAME_COLUMN));
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
        } catch (Exception ex) {
            throw new UserException("Failed to fill user", ex);
        }
    }

    private Role buildRole(ResultSet resultSet) {
        try {
            return new Role(resultSet.getLong(ROLE_ID_COLUMN),
                    resultSet.getString(ROLE_NAME_COLUMN));
        } catch (Exception ex) {
            throw new UserException("Failed to build role of user", ex);
        }
    }

    private Detail buildDetails(ResultSet resultSet) {
        try {
            return new Detail(resultSet.getLong(DETAILS_ID_COLUMN));
        } catch (Exception ex) {
            throw new UserException("Failed to build details of user", ex);
        }
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(QUERY_FIND_ALL);
        ) {
            while (resultSet.next()) {
                User user = new User();
                user.setRoles(new ArrayList<>());
                user.setDetails(new ArrayList<>());
                user.setId(resultSet.getLong(ID_COLUMN));
                user.setUsername(resultSet.getString(USER_NAME_COLUMN));
                user.setPassword(resultSet.getString(PASSWORD_COLUMN));
                user.setSurname(resultSet.getString(SURNAME_COLUMN));
                user.setName(resultSet.getString(NAME_COLUMN));
                users.add(user);
            }
        } catch (Exception ex) {
            throw new UserException("Failed to find all users", ex);
        }
        return users;
    }

    public boolean save(User user) throws Exception {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SAVE, Statement.RETURN_GENERATED_KEYS)
        ) {
            try {
                setUser(preparedStatement, user);
                preparedStatement.executeUpdate();
            } catch (Exception ex) {
                throw new UserException("Failed to save user", ex);
            }
        }
        return true;
    }

    private void setUser(PreparedStatement preparedStatement, User user) {
        try {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, user.getName());
        } catch (Exception ex) {
            throw new UserException("Failed to save user", ex);
        }
    }

    public boolean update(User user) throws Exception {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE);
        ) {
            try {
                connection.setAutoCommit(false);

                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getSurname());
                preparedStatement.setString(4, user.getName());
                preparedStatement.setLong(5, user.getId());
                preparedStatement.executeUpdate();

                connection.commit();
            } catch (Exception ex) {
                connection.rollback();
                throw new UserException("Failed to delete user", ex);
            } finally {
                connection.setAutoCommit(true);
            }
        }
        return true;
    }

    public boolean delete(Long id) throws Exception {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
        ) {
            try {
                connection.setAutoCommit(false);
                deleteUserRoleLink(connection, id);
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
        return true;
    }

    private void deleteUserRoleLink(Connection connection, Long id) throws Exception {
        try (PreparedStatement preparedStatementForRoles = connection.prepareStatement(QUERY_DELETE_USER_ROLE_LINKS)
        ) {
            try {
                preparedStatementForRoles.setLong(1, id);
                preparedStatementForRoles.executeUpdate();
            } catch (Exception ex) {
                connection.rollback();
                throw new UserException("Failed to delete links", ex);
            }
        }
    }

    private void deleteDetails(Connection connection, Long id) throws Exception {
        try (PreparedStatement preparedStatementForDetails = connection.prepareStatement(QUERY_DELETE_DETAILS)
        ) {
            try {
                preparedStatementForDetails.setLong(1, id);
                preparedStatementForDetails.executeUpdate();
            } catch (Exception ex) {
                connection.rollback();
                throw new UserException("Failed to delete links", ex);
            }
        }
    }

}

