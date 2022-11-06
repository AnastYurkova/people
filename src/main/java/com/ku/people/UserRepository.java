package com.ku.people;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public static final String FIND_BY_ID = """
            SELECT *
                FROM users 
                    LEFT JOIN user_role_links  ON users.id = user_role_links.user_id 
                    LEFT JOIN roles ON user_role_links.role_id = roles.id
                    LEFT JOIN details  ON details.user_id = users.id  
                        WHERE users.id = ?
            """;
    public static final String FIND_ALL = "SELECT * FROM users";
    public static final String SAVE = "INSERT INTO users(user_name, password, surname, name) VALUES (?, ?, ?, ?)";
    public static final String UPDATE = "UPDATE users SET user_name = ?, password = ?, surname = ?, name = ? where id = ?";
    public static final String SAVE_USER_ROLE_LINKS = "INSERT INTO user_role_links(user_id, role_id) VALUES (?,?)";
    public static final String SAVE_DETAILS = """
                INSERT INTO details(relationship_type,user_id,relationship_id) 
                    VALUES (?::relationship_type_enum,?,?)
            """;
    public static final String DELETE_DETAILS = "DELETE FROM details WHERE user_id = ? ";
    public static final String DELETE_USER_ROLE_LINKS = "DELETE FROM user_role_links WHERE user_id = ? ";
    public static final String DELETE = "DELETE FROM users WHERE id = ? ";

    private final DataSource dataSource;

    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<User> findById(Long id) {
        List<User> users = new ArrayList<>();
        List<Role> roles = new ArrayList<>();
        List<Detail> details = new ArrayList<>();
        User user = new User();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)
        ) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getLong(ID_COLUMN));
                user.setUsername(resultSet.getString(USER_NAME_COLUMN));
                user.setPassword(resultSet.getString(PASSWORD_COLUMN));
                user.setSurname(resultSet.getString(SURNAME_COLUMN));
                user.setName(resultSet.getString(NAME_COLUMN));
                if (!roles.contains(new Role(resultSet.getLong(ROLE_ID_COLUMN),
                        resultSet.getString(ROLE_NAME_COLUMN)))) {
                    roles.add(new Role(resultSet.getLong(ROLE_ID_COLUMN),
                            resultSet.getString(ROLE_NAME_COLUMN)));
                }
                if (!details.contains(new Detail(resultSet.getLong(10)))) {
                    details.add(new Detail(resultSet.getLong(10)));
                }
                user.setRoles(roles);
                user.setDetails(details);
            }
            users.add(user);
        } catch (SQLException s) {
            s.printStackTrace();
        }
        return users;
    }

    public List<User> findAll() throws UserException {
        List<User> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL);
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
        } catch (SQLException ex) {
            throw new UserException("Failed to find all users", ex);
        }
        return users;
    }

    public void saveUserRoleLinks(Connection connection, User user, Long id) throws SQLException, UserException {
        try (PreparedStatement preparedStatementForRoles = connection
                .prepareStatement(SAVE_USER_ROLE_LINKS)
        ) {
            for (int i = 0; i < user.getRoles().size(); i++) {
                preparedStatementForRoles.setLong(1, id);
                preparedStatementForRoles.setLong(2, user.getRoles().get(i).getId());
                preparedStatementForRoles.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new UserException("Failed to update user", ex);
        }
    }

    public void saveDetails(Connection connection, User user, Long id) throws SQLException, UserException {
        try (PreparedStatement preparedStatementForDetails = connection
                .prepareStatement(SAVE_DETAILS)
        ) {
            for (int i = 0; i < user.getDetails().size(); i++) {
                preparedStatementForDetails.setString(1, user.getDetails().get(i).getType());
                preparedStatementForDetails.setLong(2, id);
                preparedStatementForDetails.setLong(3, user.getDetails().get(i).getRelationship().getId());
                preparedStatementForDetails.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new UserException("Failed to update user", ex);
        }
    }

    public boolean save(User user) throws UserException, SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)
        ) {
            try {
                connection.setAutoCommit(false);

                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getSurname());
                preparedStatement.setString(4, user.getName());
                preparedStatement.executeUpdate();
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    generatedKeys.next();
                    Long id = generatedKeys.getLong(1);

                    saveUserRoleLinks(connection, user, id);
                    saveDetails(connection, user, id);
                }

                connection.commit();
            } catch (SQLException ex) {
                connection.rollback();
                throw new UserException("Failed to update user", ex);
            } finally {
                connection.setAutoCommit(true);
            }
        }
        return true;
    }

    public boolean update(User user) throws UserException, SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
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
            } catch (SQLException ex) {
                connection.rollback();
                throw new UserException("Failed to update user", ex);
            } finally {
                connection.setAutoCommit(true);
            }
        }
        return true;
    }

    public void deleteUserRoleLink(Connection connection, Long id) throws SQLException, UserException {
        try (PreparedStatement preparedStatementForRoles = connection.prepareStatement(DELETE_USER_ROLE_LINKS)
        ) {
            try {
                preparedStatementForRoles.setLong(1, id);
                preparedStatementForRoles.executeUpdate();
            } catch (SQLException ex) {
                connection.rollback();
                throw new UserException("Failed to delete links", ex);
            }
        }
    }

    public void deleteDetails(Connection connection, Long id) throws SQLException, UserException {
        try (PreparedStatement preparedStatementForDetails = connection.prepareStatement(DELETE_DETAILS)
        ) {
            try {
                preparedStatementForDetails.setLong(1, id);
                preparedStatementForDetails.executeUpdate();
            } catch (SQLException ex) {
                connection.rollback();
                throw new UserException("Failed to delete links", ex);
            }
        }
    }

    public boolean delete(Long id) throws SQLException, UserException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
        ) {
            try {
                connection.setAutoCommit(false);
                deleteUserRoleLink(connection, id);
                deleteDetails(connection, id);
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException ex) {
                connection.rollback();
                throw new UserException("Failed to delete user", ex);
            } finally {
                connection.setAutoCommit(true);
            }
        }
        return true;
    }
}

