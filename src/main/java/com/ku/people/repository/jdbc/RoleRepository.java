//package com.ku.people.repository.jdbc;
//
//import com.ku.people.entity.Authority;
//import com.ku.people.exception.RepositoryException;
//import com.ku.people.entity.Role;
//import com.ku.people.entity.User;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
//public class RoleRepository implements Repository {
//    public static final String FIND_BY_ID_QUERY = """
//        SELECT  r.id, r.role_name, url.user_id, u.user_name, u.password, u.surname, u.name, ral.authority_id, a.authority_name
//        FROM roles r
//            LEFT JOIN user_role_links url  ON r.id = url.role_id
//            LEFT JOIN users u  ON u.id = url.user_id
//            LEFT JOIN role_authority_links ral ON r.id = ral.role_id
//            LEFT JOIN authorities a  ON a.id = ral.authority_id
//        WHERE r.id = ?
//    """;
//    public static final String FIND_ALL_QUERY = "SELECT * FROM roles";
//    public static final String SAVE_QUERY = "INSERT INTO roles(role_name) VALUES (?)";
//    public static final String UPDATE_QUERY = """
//        UPDATE roles SET role_name = ? WHERE id = ?
//    """;
//    public static final String DELETE_USER_AUTHORITY_LINKS_QUERY = "DELETE FROM role_authority_links WHERE role_id = ?";
//    public static final String DELETE_USER_ROLE_LINKS_QUERY = "DELETE FROM user_role_links WHERE role_id = ?";
//    public static final String DELETE_QUERY = "DELETE FROM roles WHERE id = ?";
//
//    private final DataSource dataSource;
//
//    public RoleRepository(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    public Role findById(Long id) {
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY)
//        ) {
//            preparedStatement.setLong(1, id);
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                Role role = new Role();
//                while (resultSet.next()) {
//                    List<User> users = new ArrayList<>();
//                    List<Authority> authorities = new ArrayList<>();
//                    fillRole(role, resultSet);
//                    do {
//                        if (!users.contains(buildUser(resultSet))) {
//                            users.add(buildUser(resultSet));
//                        }
//                        if (!authorities.contains(buildAuthorities(resultSet))) {
//                            authorities.add(buildAuthorities(resultSet));
//                        }
//                    } while (resultSet.next());
//                    role.setUsers(users);
//                    role.setAuthorities(authorities);
//                }
//                return role;
//            }
//        }catch (Exception ex) {
//            throw new RepositoryException(String.format("Failed to find role where id = %d!",id), ex);
//        }
//    }
//
//    private void fillRole(Role role, ResultSet resultSet) throws Exception {
//        role.setId(resultSet.getLong(ID_COLUMN));
//        role.setName(resultSet.getString(ROLE_NAME_COLUMN));
//    }
//
//    private User buildUser(ResultSet resultSet) throws Exception {
//        return new User(resultSet.getLong(USER_ID_COLUMN),
//                resultSet.getString(USER_NAME_COLUMN),
//                resultSet.getString(PASSWORD_COLUMN),
//                resultSet.getString(SURNAME_COLUMN),
//                resultSet.getString(NAME_COLUMN));
//    }
//
//    private Authority buildAuthorities(ResultSet resultSet) throws Exception {
//        return new Authority(resultSet.getLong(AUTHORITY_ID_COLUMN),
//                resultSet.getString(AUTHORITY_NAME_COLUMN));
//    }
//
//    public List<Role> findAll() {
//        try (Connection connection = dataSource.getConnection();
//             Statement statement = connection.createStatement();
//             ResultSet resultSet = statement.executeQuery(FIND_ALL_QUERY);
//        ) {
//            List<Role> roles = new ArrayList<>();
//            while (resultSet.next()) {
//                Role role = new Role();
//                fillRole(role, resultSet);
//                roles.add(role);
//            }
//            return roles;
//        } catch (Exception ex) {
//            throw new RepositoryException("Failed to find all roles: table roles is empty", ex);
//        }
//    }
//
//    public boolean save(Role role) throws Exception {
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_QUERY)
//        ) {
//            try {
//                buildQuery(preparedStatement, role);
//                preparedStatement.executeUpdate();
//            } catch (Exception ex) {
//                throw new RepositoryException("Failed to save role: this role already exist", ex);
//            }
//        }
//        return true;
//    }
//
//    private void buildQuery(PreparedStatement preparedStatement, Role role) throws Exception {
//        preparedStatement.setString(1, role.getName());
//    }
//
//    public boolean update(Role role) throws Exception {
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
//        ) {
//            try {
//                buildQuery(preparedStatement, role);
//                preparedStatement.setLong(2, role.getId());
//                preparedStatement.executeUpdate();
//            } catch (Exception ex) {
//                String message = "Failed to update role with id = %d. This role is not exist!";
//                throw new RepositoryException(String.format(message, role.getId()), ex);
//            }
//        }
//        return true;
//    }
//
//    public boolean delete(Long id) throws Exception {
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
//        ) {
//            try {
//                connection.setAutoCommit(false);
//                deleteUserRoleLinks(connection, id);
//                deleteRoleAuthorityLinks(connection, id);
//                preparedStatement.setLong(1, id);
//                preparedStatement.executeUpdate();
//                connection.commit();
//            } catch (Exception ex) {
//                connection.rollback();
//                String message = "Failed to delete role with id = %d. This role is not exist!";
//                throw new RepositoryException(String.format(message, id), ex);
//            } finally {
//                connection.setAutoCommit(true);
//            }
//        }
//        return true;
//    }
//
//    private void deleteUserRoleLinks (Connection connection, Long id) throws Exception {
//        try (PreparedStatement preparedStatementForUserRoleLinks = connection.prepareStatement(DELETE_USER_ROLE_LINKS_QUERY)
//        ) {
//            preparedStatementForUserRoleLinks.setLong(1, id);
//            preparedStatementForUserRoleLinks.executeUpdate();
//        }
//    }
//
//    private void deleteRoleAuthorityLinks (Connection connection, Long id) throws Exception {
//        try (PreparedStatement preparedStatementForRoleAuthorityLinks = connection.prepareStatement(DELETE_USER_AUTHORITY_LINKS_QUERY)
//        ) {
//            preparedStatementForRoleAuthorityLinks.setLong(1, id);
//            preparedStatementForRoleAuthorityLinks.executeUpdate();
//        }
//    }
//}
