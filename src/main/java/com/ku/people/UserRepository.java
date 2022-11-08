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
    public static final String QUERY_SAVE_USER_ROLE_LINKS = "INSERT INTO user_role_links(user_id, role_id) VALUES (?,?)";
    public static final String QUERY_SAVE_DETAILS = """
                INSERT INTO details(relationship_type,user_id,relationship_id) 
                    VALUES (?::relationship_type_enum,?,?)
            """;
    public static final String QUERY_DELETE_DETAILS = "DELETE FROM details WHERE user_id = ? ";
    public static final String QUERY_DELETE_USER_ROLE_LINKS = "DELETE FROM user_role_links WHERE user_id = ? ";
    public static final String QUERY_DELETE = "DELETE FROM users WHERE id = ? ";

    private final DataSource dataSource;

    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public Role findRoleOfUser(ResultSet resultSet) {
        try {
            return new Role(resultSet.getLong(ROLE_ID_COLUMN),
                    resultSet.getString(ROLE_NAME_COLUMN));
        } catch (Exception ex) {
            throw new UserException("Failed to find user", ex);
        }
    }

    public Detail findDetailOfUser(ResultSet resultSet) {
        try {
            return new Detail(resultSet.getLong(DETAILS_ID_COLUMN));
        }catch (Exception ex) {
            throw new UserException("Failed to find user", ex);
        }
    }

    public List<User> findById(Long id){
        List<User> users = new ArrayList<>();
        List<Role> roles = new ArrayList<>();
        List<Detail> details = new ArrayList<>();
        User user = new User();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_FIND_BY_ID)
        ) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    user.setId(resultSet.getLong(ID_COLUMN));
                    user.setUsername(resultSet.getString(USER_NAME_COLUMN));
                    user.setPassword(resultSet.getString(PASSWORD_COLUMN));
                    user.setSurname(resultSet.getString(SURNAME_COLUMN));
                    user.setName(resultSet.getString(NAME_COLUMN));
                    if (!roles.contains(findRoleOfUser(resultSet))) {
                        roles.add(findRoleOfUser(resultSet));
                    }
                    if (!details.contains(findDetailOfUser(resultSet))) {
                        details.add(findDetailOfUser(resultSet));
                    }
                    user.setRoles(roles);
                    user.setDetails(details);
                }
                users.add(user);
            }
        } catch (Exception ex) {
            throw new UserException("Failed to find user", ex);
        }
        return users;
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

    public void saveUserRoleLinks(Connection connection, User user, Long id) {
        try (PreparedStatement preparedStatementForRoles = connection.prepareStatement(QUERY_SAVE_USER_ROLE_LINKS)) {
            for (int i = 0; i < user.getRoles().size(); i++) {
                preparedStatementForRoles.setLong(1, id);
                preparedStatementForRoles.setLong(2, user.getRoles().get(i).getId());
                preparedStatementForRoles.executeUpdate();
            }
        } catch (Exception ex) {
            throw new UserException("Failed to save user_role_links", ex);
        }
    }

    public boolean save(User user) throws Exception {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SAVE, Statement.RETURN_GENERATED_KEYS)
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
                }

                connection.commit();
            } catch (Exception ex) {
                connection.rollback();
                throw new UserException("Failed to save user", ex);
            } finally {
                connection.setAutoCommit(true);
            }
        }
        return true;
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

    public void deleteUserRoleLink(Connection connection, Long id) throws Exception {
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

    public void deleteDetails(Connection connection, Long id) throws Exception {
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
}

