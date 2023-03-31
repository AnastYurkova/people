package com.ku.people.repository;

import com.ku.people.dto.RelationshipDto;
import com.ku.people.dto.RelationshipListDto;
import com.ku.people.dto.RelationshipSaveDto;
import com.ku.people.entity.Relationship;
import com.ku.people.entity.RelationshipStatus;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Calendar;
import java.util.List;

@Repository
public class RelationshipRepository {
    public static final String FIND_BY_ID = "SELECT * FROM relationships WHERE id = :id";
    public static final String FIND_ALL = "SELECT * FROM relationships";

    public static final String SAVE =
        "INSERT INTO relationships (created_at_utc, relationship_status) " +
        "VALUES ((now() AT TIME ZONE 'UTC')::date, :status::relationship_status_enum)";
    public static final String UPDATE =
        "UPDATE relationships " +
        "SET created_at_utc = :createdAtUtc, relationship_status = :status::relationship_status_enum " +
        "WHERE id = :id";
    public static final String DELETE = "DELETE FROM relationships WHERE id = :id";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public RelationshipDto findById(Long id) {
        MapSqlParameterSource parameter = new MapSqlParameterSource().addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(FIND_BY_ID, parameter, RelationshipRepository::buildDetailDto);
    }

    @SneakyThrows
    private static RelationshipDto buildDetailDto(ResultSet resultSet, int rowNum){
        return new RelationshipDto()
                .setId(resultSet.getLong("id"))
                .setCreatedAtUtc(resultSet.getDate(2, Calendar.getInstance()).toLocalDate())
                .setStatus(RelationshipStatus.valueOf(resultSet.getString("relationship_status")));
    }

    public List<RelationshipListDto> findAll() {
        return namedParameterJdbcTemplate.query(FIND_ALL, (rs, rowNum) -> new RelationshipListDto()
                .setId(rs.getLong("id"))
                .setCreatedAtUtc(rs.getDate(2, Calendar.getInstance()).toLocalDate())
                .setStatus(RelationshipStatus.valueOf(rs.getString("relationship_status"))));
    }

    public RelationshipSaveDto save(RelationshipSaveDto relationshipSaveDto) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameter = new MapSqlParameterSource()
                .addValue("status", relationshipSaveDto.getStatus().toString());
        namedParameterJdbcTemplate.update(SAVE, parameter, keyHolder, new String[]{"id"});
        return relationshipSaveDto.setId((Long) keyHolder.getKey());
    }

    public void update(RelationshipSaveDto relationshipSaveDto) {
        MapSqlParameterSource parameter = new MapSqlParameterSource()
                .addValue("createdAtUtc", relationshipSaveDto.getCreatedAtUtc())
                .addValue("status", relationshipSaveDto.getStatus().toString())
                .addValue("id", relationshipSaveDto.getId());
        namedParameterJdbcTemplate.update(UPDATE,parameter);
    }

    public void delete(Long id) {
        MapSqlParameterSource parameter = new MapSqlParameterSource().addValue("id", id);
        namedParameterJdbcTemplate.update(DELETE, parameter);
    }
}
