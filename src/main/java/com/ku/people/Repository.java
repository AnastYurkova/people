package com.ku.people;

public interface Repository {
    String ID_COLUMN = "id";
    String USER_NAME_COLUMN = "user_name";
    String PASSWORD_COLUMN = "password";
    String SURNAME_COLUMN = "surname";
    String NAME_COLUMN = "name";
    String ROLE_ID_COLUMN = "role_id";
    String ROLE_NAME_COLUMN = "role_name";
    String DETAIL_ID_COLUMN = "detail_id";

    String FIND_BY_ID_QUERY = """
                SELECT u.id, u.user_name, u.password, u.surname, u.name, r.id role_id, r.role_name, d.id detail_id
                FROM users u
                    LEFT JOIN user_role_links url  ON u.id = url.user_id
                    LEFT JOIN roles r ON url.role_id = r.id
                    LEFT JOIN details d ON d.user_id = u.id 
                WHERE u.id = ?
            """;
    String FIND_ALL_QUERY = "SELECT * FROM users";
    String SAVE_QUERY = """
                INSERT INTO users(user_name, password, surname, name) VALUES (?, ?, ?, ?)
            """;
    String UPDATE_QUERY = """
                UPDATE users SET user_name = ?, password = ?, surname = ?, name = ? WHERE id = ?
            """;
    String DELETE_DETAIL_QUERY = "DELETE FROM details WHERE user_id = ?";
    String DELETE_USER_ROLE_LINKS_QUERY = "DELETE FROM user_role_links WHERE user_id = ?";
    String DELETE_QUERY = "DELETE FROM users WHERE id = ?";
}
