package com.ku.people.service;

import com.ku.people.dto.DetailDto;
import com.ku.people.dto.DetailListDto;
import com.ku.people.dto.DetailSaveDto;
import com.ku.people.repository.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailService {

    private DetailRepository detailRepository;


    public DetailDto findById(Long id) {
        return detailRepository.findById(id);
    }

    public List<DetailListDto> findAll() {
        return detailRepository.findAll();
    }

    public DetailSaveDto save(DetailSaveDto detailSaveDto) {
        return detailRepository.save(detailSaveDto);
    }

    public void update(DetailSaveDto detailSaveDto) {
       detailRepository.update(detailSaveDto);
    }

    public void delete(Long id) {
        detailRepository.delete(id);
    }

    @Autowired
    public void setDetailRepository(DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }
}
