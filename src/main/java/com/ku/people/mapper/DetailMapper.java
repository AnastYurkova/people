package com.ku.people.mapper;

import com.ku.people.dto.DetailDto;
import com.ku.people.dto.DetailListDto;
import com.ku.people.dto.DetailSaveDto;
import com.ku.people.entity.Detail;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DetailMapper {

    public static DetailDto toDto(Detail detail) {
        DetailDto detailDto = new DetailDto()
                .setId(detail.getId())
                .setUser(UserMapper.toDto(detail.getUser()))
                .setRelationship(RelationshipMapper.toDto(detail.getRelationship()));
        detailDto.setType(detail.getType());
        return detailDto;
    }

    public static List<DetailListDto> toListDto(List<Detail> details) {
        List<DetailListDto> detailListDtos = new ArrayList<>();
        for (Detail detail : details) {
            detailListDtos.add(toListDto(detail));
        }
        return detailListDtos;
    }

    public static Set<DetailListDto> toListDto(Set<Detail> details) {
        List<DetailListDto> detailListDtos = new ArrayList<>();
        for (Detail detail : details) {
            detailListDtos.add(toListDto(detail));
        }
        return new HashSet<>(detailListDtos);
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
