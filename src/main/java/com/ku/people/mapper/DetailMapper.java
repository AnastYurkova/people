package com.ku.people.mapper;

import com.ku.people.dto.DetailDto;
import com.ku.people.dto.DetailListDto;
import com.ku.people.dto.DetailSaveDto;
import com.ku.people.entity.Detail;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DetailMapper {
    public DetailDto toDto(Detail detail) {
        DetailDto detailDto = new DetailDto();
        detailDto.setId(detail.getId());
        detailDto.setUser(detail.getUser());
        detailDto.setRelationship(detail.getRelationship());
        detailDto.setType(detail.getType());
        return detailDto;
    }

    public List<DetailListDto> toListDto(List<Detail> details) {
        List<DetailListDto> detailListDtos = new ArrayList<>();
        for (Detail detail : details) {
            DetailListDto detailListDto = new DetailListDto();
            detailListDto.setId(detail.getId());
            detailListDto.setType(detail.getType());
            detailListDtos.add(detailListDto);
        }
        return detailListDtos;
    }

    public Detail fromSaveDto(DetailSaveDto roleSaveDto) {
        Detail detail = new Detail();
        detail.setType(roleSaveDto.getType());
        detail.setRelationship(roleSaveDto.getRelationship());
        detail.setUser(roleSaveDto.getUser());
        return detail;
    }
}
