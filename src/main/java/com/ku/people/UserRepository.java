package com.ku.people;

import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    public static final String FIND_USER_BY_ID = "SELECT * FROM users " +
            "LEFT JOIN user_role_links  ON users.id = user_role_links.user_id " +
            "LEFT JOIN details  ON details.user_id = users.id  WHERE users.id = ?";
    public static final String FIND_ALL_USERS = "SELECT * FROM users";
    public static final String FIND_DETAILS_ID = "SELECT id FROM details WHERE user_id = ?";
    public static final String FIND_ROLES_ID = "SELECT role_id FROM user_role_links WHERE user_id = ?";
    public static final String ADD_USER = "INSERT INTO users(user_name, password, surname, name) VALUES (?, ?, ?, ?)";
    public static final String ADD_USER_ROLE_LINKS = "INSERT INTO user_role_links(user_id, role_id) VALUES (?,?)";
    public static final String ADD_DETAILS = "INSERT INTO details(relationship_type,user_id,relationship_id) VALUES (?::relationship_type_enum,?,?)";
    public static final String UPDATE_USER = "UPDATE users SET user_name = ?, password = ?, surname = ?, name = ? where id = ?";
    public static final String DELETE_DETAILS = "DELETE FROM details WHERE user_id = ? ";
    public static final String DELETE_USER_ROLE_LINKS = "DELETE FROM user_role_links WHERE user_id = ? ";
    public static final String DELETE_USERS = "DELETE FROM users WHERE id = ? ";

    private final DataSource dataSource;

    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<User> findById(Long id) throws SQLException {
        List<User> users = new ArrayList<>();
        List<Role> roles = new ArrayList<>();
        List<Detail> details = new ArrayList<>();
        User user = new User();

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user.setId(resultSet.getLong("id"));
                user.setUsername(resultSet.getString("user_name"));
                user.setPassword(resultSet.getString("password"));
                user.setSurname(resultSet.getString("surname"));
                user.setName(resultSet.getString("name"));

                if (!roles.contains(new Role(resultSet.getLong("role_id")))) {
                    roles.add(new Role(resultSet.getLong("role_id")));
                }
                if (!details.contains(new Detail(resultSet.getLong(8)))) {
                    details.add(new Detail(resultSet.getLong(8)));
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

    public List<User> findAll() throws UserException, SQLException {
        List<User> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(FIND_ALL_USERS);
             PreparedStatement preparedStatementForRoles = connection.prepareStatement(FIND_ROLES_ID);
             PreparedStatement preparedStatementForDetails = connection.prepareStatement(FIND_DETAILS_ID);

        ) {
            try {
                while (resultSet.next()) {
                    preparedStatementForDetails.setLong(1, resultSet.getLong("id"));

                    preparedStatementForRoles.setLong(1, resultSet.getLong("id"));

                    User user = new User();
                    user.setRoles(new ArrayList<>());
                    user.setDetails(new ArrayList<>());
                    user.setId(resultSet.getLong("id"));
                    user.setUsername(resultSet.getString("user_name"));
                    user.setPassword(resultSet.getString("password"));
                    user.setSurname(resultSet.getString("surname"));
                    user.setName(resultSet.getString("name"));

                    ResultSet resultSetForRoles = preparedStatementForRoles.executeQuery();
                    while (resultSetForRoles.next()) {
                        user.getRoles().add(new Role(resultSetForRoles.getLong(1)));
                    }

                    ResultSet resultSetForDetails = preparedStatementForDetails.executeQuery();
                    while (resultSetForDetails.next()) {
                        user.getDetails().add(new Detail(resultSetForDetails.getLong(1)));
                    }
                    users.add(user);
                }
            } catch (SQLException ex) {
                throw new UserException("Failed to find all users", ex);
            }
        }
        return users;
    }

    public boolean add(User user) throws UserException, SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement preparedStatementForRoles = connection.prepareStatement(ADD_USER_ROLE_LINKS);
             PreparedStatement preparedStatementForDetails = connection.prepareStatement(ADD_DETAILS);
        ) {
            try {
                connection.setAutoCommit(false);

                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getSurname());
                preparedStatement.setString(4, user.getName());
                preparedStatement.executeUpdate();

                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                generatedKeys.next();
                Long userId = generatedKeys.getLong(1);

                for (int i = 0; i < user.getRoles().size(); i++) {
                    preparedStatementForRoles.setLong(1, userId);
                    preparedStatementForRoles.setLong(2, user.getRoles().get(i).getId());
                    preparedStatementForRoles.executeUpdate();
                }

                for (int i = 0; i < user.getDetails().size(); i++) {
                    preparedStatementForDetails.setString(1, user.getDetails().get(i).getType());
                    preparedStatementForDetails.setLong(2, userId);
                    preparedStatementForDetails.setLong(3, user.getDetails().get(i).getRelationship().getId());
                    preparedStatementForDetails.executeUpdate();

                    connection.commit();
                    connection.setAutoCommit(true);
                }
            } catch (SQLException ex) {
                connection.rollback();
                throw new UserException("Failed to update user", ex);
            }
        }
        return true;
    }

    public boolean update(User user) throws UserException, SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER);
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
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                connection.rollback();
                throw new UserException("Failed to update user", ex);
            }
        }
        return true;
    }

    public void deleteLinks(Connection connection, Long id) throws SQLException, UserException {
        try (PreparedStatement preparedStatementForDetails = connection.prepareStatement(DELETE_DETAILS);
             PreparedStatement preparedStatementForRoles = connection.prepareStatement(DELETE_USER_ROLE_LINKS)
        ) {
            try {
                preparedStatementForDetails.setLong(1, id);
                preparedStatementForDetails.executeUpdate();
                preparedStatementForRoles.setLong(1, id);
                preparedStatementForRoles.executeUpdate();
            } catch (SQLException ex) {
                connection.rollback();
                throw new UserException("Failed to delete links", ex);
            }
        }
    }

    public boolean delete(Long id) throws SQLException, UserException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USERS);
        ) {
            try {
                connection.setAutoCommit(false);
                deleteLinks(connection, id);
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                connection.rollback();
                throw new UserException("Failed to delete user", ex);
            }
        }
        return true;
    }
}

