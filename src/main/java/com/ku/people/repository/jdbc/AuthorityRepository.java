//package com.ku.people.repository.jdbc;
//
//import com.ku.people.entity.Authority;
//import com.ku.people.exception.RepositoryException;
//import com.ku.people.entity.Role;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
//public class AuthorityRepository implements Repository {
//    public static final String FIND_BY_ID_QUERY = """
//        SELECT a.id, a.authority_name, ral.role_id, r.role_name
//        FROM authorities a
//            LEFT JOIN role_authority_links ral ON a.id = ral.authority_id
//            LEFT JOIN roles r ON r.id = ral.role_id
//        WHERE a.id = ?
//    """;
//    public static final String FIND_ALL_QUERY = "SELECT * FROM authorities";
//    public static final String SAVE_QUERY = "INSERT INTO authorities(authority_name) VALUES (?)";
//    public static final String UPDATE_QUERY = """
//        UPDATE authorities SET authority_name = ? WHERE id = ?
//    """;
//    public static final String DELETE_USER_AUTHORITY_LINKS_QUERY = "DELETE FROM role_authority_links WHERE authority_id = ?";
//    public static final String DELETE_QUERY = "DELETE FROM authorities WHERE id = ?";
//
//    private final DataSource dataSource;
//
//    public AuthorityRepository(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    public Authority findById(Long id) {
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY)
//        ) {
//            preparedStatement.setLong(1, id);
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                Authority authority = new Authority();
//                while (resultSet.next()) {
//                    List<Role> roles = new ArrayList<>();
//                    fillAuthority(authority, resultSet);
//                    do {
//                        if (!roles.contains(buildRoles(resultSet))) {
//                            roles.add(buildRoles(resultSet));
//                        }
//                    } while (resultSet.next());
//                    authority.setRoles(roles);
//                }
//                return authority;
//            }
//        } catch (Exception ex) {
//            throw new RepositoryException(String.format("Failed to find authority where id = %d!", id), ex);
//        }
//    }
//
//    private void fillAuthority(Authority authority, ResultSet resultSet) throws Exception {
//        authority.setId(resultSet.getLong(ID_COLUMN));
//        authority.setAuthorityName(resultSet.getString(AUTHORITY_NAME_COLUMN));
//    }
//
//    private Role buildRoles(ResultSet resultSet) throws Exception {
//        return new Role(resultSet.getLong(ROLE_ID_COLUMN),
//                resultSet.getString(ROLE_NAME_COLUMN));
//    }
//
//    public List<Authority> findAll() {
//        try (Connection connection = dataSource.getConnection();
//             Statement statement = connection.createStatement();
//             ResultSet resultSet = statement.executeQuery(FIND_ALL_QUERY);
//        ) {
//            List<Authority> authorities = new ArrayList<>();
//            while (resultSet.next()) {
//                Authority authority = new Authority();
//                fillAuthority(authority, resultSet);
//                authorities.add(authority);
//            }
//            return authorities;
//        } catch (Exception ex) {
//            throw new RepositoryException("Failed to find all authorities: table authorities is empty", ex);
//        }
//    }
//
//    public boolean save(Authority authority) throws Exception {
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_QUERY)
//        ) {
//            try {
//                preparedStatement.setString(1, authority.getAuthorityName());
//                preparedStatement.executeUpdate();
//            } catch (Exception ex) {
//                throw new RepositoryException("Failed to save authority: this authority already exist", ex);
//            }
//        }
//        return true;
//    }
//
//    public boolean update(Authority authority) throws Exception {
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
//        ) {
//            try {
//                preparedStatement.setString(1, authority.getAuthorityName());
//                preparedStatement.setLong(2, authority.getId());
//                preparedStatement.executeUpdate();
//            } catch (Exception ex) {
//                String message = "Failed to update authority with id=%d. This authority is not exist!";
//                throw new RepositoryException(String.format(message, authority.getId()), ex);
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
//                deleteRoleAuthorityLinks(connection, id);
//                preparedStatement.setLong(1, id);
//                preparedStatement.executeUpdate();
//                connection.commit();
//            } catch (Exception ex) {
//                connection.rollback();
//                String message = "Failed to delete authority with id=%d. This authority is not exist!";
//                throw new RepositoryException(String.format(message, id), ex);
//            } finally {
//                connection.setAutoCommit(true);
//            }
//        }
//        return true;
//    }
//
//    private void deleteRoleAuthorityLinks (Connection connection, Long id) throws Exception {
//        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_AUTHORITY_LINKS_QUERY)
//        ) {
//            preparedStatement.setLong(1, id);
//            preparedStatement.executeUpdate();
//        }
//    }
//}
