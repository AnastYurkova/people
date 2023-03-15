package com.ku.people.repository;

import com.ku.people.dto.RoleDto;
import com.ku.people.dto.RoleListDto;
import com.ku.people.dto.RoleSaveDto;
import com.ku.people.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepository {
    public static final String FIND_BY_ID = "SELECT * FROM roles WHERE id = :id";
    public static final String FIND_ALL = "SELECT * FROM roles";

    public static final String SAVE = "INSERT INTO roles (role_name) VALUES (:name)";
    public static final String UPDATE = "UPDATE roles SET role_name = :name WHERE id = :id";
    public static final String DELETE = "DELETE FROM roles WHERE id = :id";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public RoleDto findById(Long id) {
        MapSqlParameterSource parameter = new MapSqlParameterSource().addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(FIND_BY_ID, parameter, (rs, rowNum) -> new RoleDto()
                .setId(rs.getLong("id"))
                .setName(rs.getString("role_name")));
    }

    public List<RoleListDto> findAll() {
        return namedParameterJdbcTemplate.query(FIND_ALL, (rs, rowNum) -> new RoleListDto()
                .setId(rs.getLong("id"))
                .setName(rs.getString("role_name")));
    }

    public RoleSaveDto save(RoleSaveDto roleSaveDto) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameter = new MapSqlParameterSource().addValue("name", roleSaveDto.getName());
        namedParameterJdbcTemplate.update(SAVE,parameter, keyHolder, new String[]{"id"});
        return roleSaveDto.setId((Long) keyHolder.getKey());
    }

    public void update(RoleSaveDto roleSaveDto) {
        MapSqlParameterSource parameter = new MapSqlParameterSource()
                .addValue("name", roleSaveDto.getName())
                .addValue("id", roleSaveDto.getId());
        namedParameterJdbcTemplate.update(UPDATE,parameter);
    }

    public void delete(Long id) {
        MapSqlParameterSource parameter = new MapSqlParameterSource().addValue("id", id);
        namedParameterJdbcTemplate.update(DELETE, parameter);
    }
}
