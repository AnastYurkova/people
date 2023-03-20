package com.ku.people.repository;

import com.ku.people.dto.DetailDto;
import com.ku.people.dto.DetailListDto;
import com.ku.people.dto.DetailSaveDto;
import com.ku.people.entity.Detail;
import com.ku.people.entity.RelationshipType;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class DetailRepository {

    public static final String FIND_BY_ID = "SELECT * FROM details WHERE id = :id";
    public static final String FIND_ALL = "SELECT * FROM details";

    public static final String SAVE =
        "INSERT INTO details (relationship_type, user_id, relationship_id) " +
        "VALUES (:type::relationship_type_enum, :userId, :relationshipId)";
    public static final String UPDATE =
         "UPDATE details " +
         "SET relationship_type = :type::relationship_type_enum, user_id = :userId, relationship_id = :relationshipId " +
         "WHERE id = :id";
    public static final String DELETE = "DELETE FROM users WHERE id = :id";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public DetailDto findById(Long id) {
        MapSqlParameterSource parameter = new MapSqlParameterSource().addValue("id", id);
        return namedParameterJdbcTemplate.queryForObject(FIND_BY_ID, parameter, this::buildDetailDto);
    }

    @SneakyThrows
    public DetailDto buildDetailDto(ResultSet resultSet, int rowNum){
        return new DetailDto()
                .setId(resultSet.getLong("id"))
                .setType(RelationshipType.valueOf(resultSet.getString("relationship_type")))
                .setUserId(resultSet.getLong("user_id"))
                .setRelationshipId(resultSet.getLong("relationship_id"));
    }

    public List<DetailListDto> findAll() {
        return namedParameterJdbcTemplate.query(FIND_ALL, (rs, rowNum) -> new DetailListDto()
                .setId(rs.getLong("id"))
                .setType(RelationshipType.valueOf(rs.getString("relationship_type"))));
    }

    public DetailSaveDto save(DetailSaveDto detailSaveDto) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(SAVE,fillParameters(detailSaveDto), keyHolder, new String[]{"id"});
        return detailSaveDto.setId((Long) keyHolder.getKey());
    }

    private MapSqlParameterSource fillParameters(DetailSaveDto detailSaveDto) {
        return new MapSqlParameterSource()
                .addValue("type", detailSaveDto.getType().toString())
                .addValue("userId", detailSaveDto.getUserId())
                .addValue("relationshipId", detailSaveDto.getRelationshipId());
    }

    public void update(DetailSaveDto detailSaveDto) {
        MapSqlParameterSource parameter = fillParameters(detailSaveDto).addValue("id", detailSaveDto.getId());
        namedParameterJdbcTemplate.update(UPDATE,parameter);
    }

    public void delete(Long id) {
        MapSqlParameterSource parameter = new MapSqlParameterSource().addValue("id", id);
        namedParameterJdbcTemplate.update(DELETE, parameter);
    }
}
