package com.ku.people;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private DBWorwer dbWorwer;

    public UserRepository(DBWorwer dbWorwer) {
        this.dbWorwer = dbWorwer;
    }

    public List<User> findById(Long id) throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection connection = dbWorwer.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users where id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                PreparedStatement preparedStatementForDetails = connection
                        .prepareStatement("SELECT id from details where user_id = ?");
                preparedStatementForDetails.setLong(1, id);

                PreparedStatement preparedStatementForRoles = connection
                        .prepareStatement("SELECT role_id from user_role_links where user_id = ?");
                preparedStatementForRoles.setLong(1, id);

                User user = new User();
                user.setRoles(new ArrayList<>());
                user.setDetails(new ArrayList<>());
                user.setId(resultSet.getLong(1));
                user.setUsername(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setSurname(resultSet.getString(4));
                user.setName(resultSet.getString(5));

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
        } catch (SQLException s) {
            s.printStackTrace();
        }
        return users;
    }

    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection connection = dbWorwer.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                PreparedStatement preparedStatementForDetails = connection
                        .prepareStatement("SELECT id from details where user_id = ?");
                preparedStatementForDetails.setLong(1, resultSet.getLong(1));

                PreparedStatement preparedStatementForRoles = connection
                        .prepareStatement("SELECT role_id from user_role_links where user_id = ?");
                preparedStatementForRoles.setLong(1, resultSet.getLong(1));

                User user = new User();
                user.setRoles(new ArrayList<>());
                user.setDetails(new ArrayList<>());
                user.setId(resultSet.getLong(1));
                user.setUsername(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setSurname(resultSet.getString(4));
                user.setName(resultSet.getString(5));

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean add(User user) throws SQLException {
        String s = "insert into users(user_name, password, surname, name) values (?, ?, ?, ?)";
        try (Connection connection = dbWorwer.getConnection()) {
            try {
                connection.setAutoCommit(false);

                PreparedStatement preparedStatement = connection.prepareStatement(s, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getSurname());
                preparedStatement.setString(4, user.getName());
                preparedStatement.executeUpdate();

                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                generatedKeys.next();
                Long userId = generatedKeys.getLong(1);

                for (int i = 0; i < user.getRoles().size(); i++) {
                    PreparedStatement preparedStatementForRoles = connection
                            .prepareStatement("insert into user_role_links(user_id, role_id) values (?,?)");
                    preparedStatementForRoles.setLong(1, userId);
                    preparedStatementForRoles.setLong(2, user.getRoles().get(i).getId());
                    preparedStatementForRoles.executeUpdate();
                }

                for (int i = 0; i < user.getDetails().size(); i++) {
                    PreparedStatement preparedStatementForDetails = connection
                            .prepareStatement(
                                    "insert into details(relationship_type,user_id,relationship_id) values " +
                                            "(?::relationship_type_enum,?,?)");
                    preparedStatementForDetails.setString(1, user.getDetails().get(i).getType());
                    preparedStatementForDetails.setLong(2, userId);
                    preparedStatementForDetails.setLong(3, user.getDetails().get(i).getRelationship().getId());
                    preparedStatementForDetails.executeUpdate();

                    connection.commit();
//                connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                connection.rollback();
            }
        }
        return true;
    }

    public boolean update(User user) throws SQLException {
        String s = "update users set user_name = ?, password = ?, surname = ?, name = ? where id = ?";
        try (Connection connection = dbWorwer.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(s);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setLong(5, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(Long id) throws SQLException {
        String s = "delete from details where user_id = ? ";
        String s1 = "delete from user_role_links where user_id = ? ";
        String s2 = "delete from users where id = ? ";
        try (Connection connection = dbWorwer.getConnection()) {
            PreparedStatement preparedStatementForDetails = connection.prepareStatement(s);
            preparedStatementForDetails.setLong(1, id);
            preparedStatementForDetails.executeUpdate();

            PreparedStatement preparedStatementForUserRoleLinks = connection.prepareStatement(s1);
            preparedStatementForUserRoleLinks.setLong(1, id);
            preparedStatementForUserRoleLinks.executeUpdate();

            PreparedStatement preparedStatement = connection.prepareStatement(s2);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

}

