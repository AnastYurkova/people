package com.ku.people.repository;

import com.ku.people.dto.AuthorityDto;
import com.ku.people.dto.AuthorityListDto;
import com.ku.people.dto.AuthoritySaveDto;
import com.ku.people.dto.UserDto;
import com.ku.people.dto.UserListDto;
import com.ku.people.entity.Authority;
import com.ku.people.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorityRepository {
    public static final String FIND_BY_ID = "SELECT * FROM authorities WHERE id = :id";
    public static final String FIND_ALL = "SELECT * FROM authorities";

    public static final String SAVE = "INSERT INTO authorities (authority_name) VALUES (:authorityName)";
    public static final String UPDATE = "UPDATE authorities SET authority_name = :authorityName WHERE id = :id";
    public static final String DELETE = "DELETE FROM authorities WHERE id = :id";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public AuthorityDto findById(Long id) {
        MapSqlParameterSource parameter = new MapSqlParameterSource().addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(FIND_BY_ID, parameter, (rs, rowNum) -> new AuthorityDto()
                .setId(rs.getLong("id"))
                .setAuthorityName(rs.getString("authority_name")));
    }

    public List<AuthorityListDto> findAll() {
        return namedParameterJdbcTemplate.query(FIND_ALL, (rs, rowNum) -> new AuthorityListDto()
                .setId(rs.getLong("id"))
                .setAuthorityName(rs.getString("authority_name")));
    }

    public AuthoritySaveDto save(AuthoritySaveDto authoritySaveDto) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameter = new MapSqlParameterSource()
                .addValue("authorityName", authoritySaveDto.getAuthorityName());
        namedParameterJdbcTemplate.update(SAVE,parameter, keyHolder, new String[]{"id"});
        return authoritySaveDto.setId((Long) keyHolder.getKey());
    }

    public void update(AuthoritySaveDto authoritySaveDto) {
        MapSqlParameterSource parameter = new MapSqlParameterSource()
                .addValue("authorityName", authoritySaveDto.getAuthorityName())
                .addValue("id", authoritySaveDto.getId());
        namedParameterJdbcTemplate.update(UPDATE,parameter);
    }

    public void delete(Long id) {
        MapSqlParameterSource parameter = new MapSqlParameterSource().addValue("id", id);
        namedParameterJdbcTemplate.update(DELETE, parameter);
    }

}
