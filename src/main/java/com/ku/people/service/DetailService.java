package com.ku.people.service;


import com.ku.people.entity.Detail;
import com.ku.people.repository.dataJPA.DetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DetailService {

    private final DetailRepository detailRepository;

    public DetailService(DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }

    public Optional<Detail> findById(Long id) {
        return detailRepository.findById(id);
    }

    public List<Detail> findAll() {
        return detailRepository.findAll();
    }

    public void save(Detail relationship) {
        detailRepository.save(relationship);
    }

    public void update(Detail relationship) {
        detailRepository.save(relationship);
    }

    public void delete(Detail detail) {
        detailRepository.delete(detail);
    }

}
