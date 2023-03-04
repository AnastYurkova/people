package com.ku.people.mapper;

import com.ku.people.dto.DetailDto;
import com.ku.people.dto.DetailListDto;
import com.ku.people.dto.DetailSaveDto;
import com.ku.people.entity.Detail;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DetailMapper {

    public static DetailDto toDto(Detail detail) {
        return new DetailDto()
                .setId(detail.getId())
                .setUser(UserMapper.toDto(detail.getUser()))
                .setRelationship(RelationshipMapper.toDto(detail.getRelationship()))
                .setType(detail.getType());
    }

    public static List<DetailListDto> toListDto(List<Detail> details) {
        return details.stream()
                .map(DetailMapper::toListDto)
                .collect(Collectors.toList());
    }

    public static Set<DetailListDto> toListDto(Set<Detail> details) {
        return details.stream()
                .map(DetailMapper::toListDto)
                .collect(Collectors.toSet());
    }

    public static DetailListDto toListDto(Detail detail) {
        return new DetailListDto()
                .setId(detail.getId())
                .setType(detail.getType());
    }

    public static Detail fromSaveDto(DetailSaveDto roleSaveDto) {
        return new Detail()
                .setType(roleSaveDto.getType());
    }
}
