package com.ku.people.service;

import com.ku.people.dto.DetailDto;
import com.ku.people.dto.DetailListDto;
import com.ku.people.dto.DetailSaveDto;
import com.ku.people.dto.UserDto;
import com.ku.people.dto.UserListDto;
import com.ku.people.dto.UserSaveDto;
import com.ku.people.entity.Detail;
import com.ku.people.entity.User;
import com.ku.people.mapper.DetailMapper;
import com.ku.people.repository.DetailRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class DetailService {

    private  DetailRepository detailRepository;

    private DetailMapper detailMapper;

    public DetailDto findById(Long id) {
        Detail detail = detailRepository.findById(id).get();
        return detailMapper.toDto(detail);
    }

    public List<DetailListDto> findAll() {
        List<Detail> details = detailRepository.findAll();
        return detailMapper.toListDto(details);
    }

    public Detail save(DetailSaveDto detailSaveDto) {
        Detail detail = detailMapper.fromSaveDto(detailSaveDto);
        return detailRepository.save(detail);
    }

}
