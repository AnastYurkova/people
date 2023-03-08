package com.ku.people.service;

import com.ku.people.dto.DetailDto;
import com.ku.people.dto.DetailListDto;
import com.ku.people.dto.DetailSaveDto;
import com.ku.people.entity.Detail;
import com.ku.people.mapper.DetailMapper;
import com.ku.people.repository.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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

    public void update(DetailSaveDto detailSaveDto) {
        Detail updated = DetailMapper.fromSaveDtoForUpdate(detailSaveDto);
        detailRepository.save(updated);
    }

    public void delete(Long id) {
        Optional<Detail> optional = detailRepository.findById(id);
        if (optional.isPresent()) {
            Detail detail = optional.get();
            detailRepository.delete(detail);
        }
    }

    @Autowired
    public void setDetailRepository(DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }
}
