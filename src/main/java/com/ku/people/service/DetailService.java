package com.ku.people.service;

import com.ku.people.dto.DetailDto;
import com.ku.people.dto.DetailListDto;
import com.ku.people.dto.DetailSaveDto;
import com.ku.people.entity.Detail;
import com.ku.people.mapper.DetailMapper;
import com.ku.people.repository.DetailRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DetailService {

    private DetailRepository detailRepository;


    public DetailDto findById(Long id) {
        Detail detail = detailRepository.findById(id).get();
        return DetailMapper.toDto(detail);
    }

    public List<DetailListDto> findAll() {
        List<Detail> details = detailRepository.findAll();
        return DetailMapper.toListDto(details);
    }

    public Detail save(DetailSaveDto detailSaveDto) {
        Detail detail = DetailMapper.fromSaveDto(detailSaveDto);
        return detailRepository.save(detail);
    }

    @Autowired
    public void setDetailRepository(DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }
}
