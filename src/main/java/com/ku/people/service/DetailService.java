package com.ku.people.service;


import com.ku.people.entity.Detail;
import com.ku.people.repository.hibernate.DetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DetailService {

    private final DetailRepository detailRepository;

    public DetailService(DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }

    public Detail findById(Long id) {
        return detailRepository.findById(id);
    }

    public List<Detail> findAll() {
        return detailRepository.findAll();
    }

    public void save(Detail relationship) {
        detailRepository.save(relationship);
    }

    public void update(Detail relationship) {
        detailRepository.update(relationship);
    }

    public void delete(Long id) {
        detailRepository.delete(id);
    }

}
