package com.ku.people.repository;

import com.ku.people.dto.UserDto;
import com.ku.people.dto.UserListDto;
import com.ku.people.dto.UserSaveDto;
import com.ku.people.filter.UserFilter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@Repository
public class UserRepository {
    public static final String FIND_BY_ID = "SELECT * FROM users WHERE id = :id";
    public static final String FIND_ALL = "SELECT * FROM users";
    public static final String FIND_ALL_BY_FILTER =
        "SELECT u.id, u.name, u.surname, u.user_name " +
            "FROM users u " +
        "WHERE (:nameIsNull OR u.name = :name) " +
            "AND (:surnameIsNull OR u.surname = :surname) " +
            "AND (:usernameIsNull OR u.user_name = :username) " +
        "LIMIT :limit " +
        "OFFSET :offset";
    public static final String SAVE =
        "INSERT INTO users (name, surname, user_name, password) " +
        "VALUES (:name, :surname, :username, :password)";
    public static final String UPDATE =
        "UPDATE users " +
        "SET name = :name, surname = :surname, user_name = :username, password = :password " +
        "WHERE id = :id";
    public static final String DELETE = "DELETE FROM users WHERE id = :id";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public UserDto findById(Long id) {
        MapSqlParameterSource parameter = new MapSqlParameterSource().addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(FIND_BY_ID, parameter, (rs, rowNum) -> new UserDto()
                .setId(rs.getLong("id"))
                .setName(rs.getString("name"))
                .setSurname(rs.getString("surname"))
                .setUsername(rs.getString("user_name")));
    }

    public List<UserListDto> findAll() {
        return namedParameterJdbcTemplate.query(FIND_ALL,  (rs, rowNum) ->  new UserListDto()
                .setId(rs.getLong("id"))
                .setName(rs.getString("name"))
                .setSurname(rs.getString("surname"))
                .setUsername(rs.getString("user_name")));
    }

    public List<UserDto> search(UserFilter userFilter) {
        return namedParameterJdbcTemplate.query(FIND_ALL_BY_FILTER, fillFilteredParameters(userFilter), this::fillUserDto);
    }

    @SneakyThrows
    private UserDto fillUserDto(ResultSet rs, int rowNum) {
        return new UserDto()
                .setId(rs.getLong("id"))
                .setName(rs.getString("name"))
                .setSurname(rs.getString("surname"))
                .setUsername(rs.getString("user_name"));
    }

    private static MapSqlParameterSource fillFilteredParameters(UserFilter userFilter) {
        return new MapSqlParameterSource()
                .addValue("nameIsNull", userFilter.getName() == null)
                .addValue("name", userFilter.getName())
                .addValue("surnameIsNull", userFilter.getSurname() == null)
                .addValue("surname", userFilter.getSurname())
                .addValue("usernameIsNull", userFilter.getUsername() == null)
                .addValue("username", userFilter.getUsername())
                .addValue("limit", userFilter.getLimit())
                .addValue("offset", userFilter.getOffset());
    }

    public UserSaveDto save(UserSaveDto userSaveDto) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(SAVE,fillParameters(userSaveDto), keyHolder, new String[]{"id"});
        return userSaveDto.setId((Long) keyHolder.getKey());
    }

    private MapSqlParameterSource fillParameters(UserSaveDto userSaveDto) {
        return new MapSqlParameterSource()
                .addValue("name", userSaveDto.getName())
                .addValue("surname", userSaveDto.getSurname())
                .addValue("username", userSaveDto.getUsername())
                .addValue("password", userSaveDto.getPassword());
    }

    public void update(UserSaveDto userSaveDto) {
        MapSqlParameterSource parameter = fillParameters(userSaveDto).addValue("id", userSaveDto.getId());
        namedParameterJdbcTemplate.update(UPDATE,parameter);

    }

    public void delete(Long id) {
        MapSqlParameterSource parameter = new MapSqlParameterSource().addValue("id", id);
        namedParameterJdbcTemplate.update(DELETE, parameter);
    }
}
