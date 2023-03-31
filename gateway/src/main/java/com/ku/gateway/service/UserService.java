package com.ku.gateway.service;

import com.ku.gateway.dto.UserSaveDto;
import com.ku.gateway.filter.UserFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    private RestTemplate restTemplate;

    public String findById(Long id){
        StringBuilder resourceUrl = new StringBuilder("http://localhost:8080/users/");
        if (id != null){
            resourceUrl.append(id);
        }
        return restTemplate.getForObject(resourceUrl.toString(),String.class);
    }

    public String search(UserFilter userFilter){
        StringBuilder resourceUrl = new StringBuilder("http://localhost:8080/users/?");

        userFilter.getName().ifPresent(name -> resourceUrl.append("&name=").append(name));
        userFilter.getSurname().ifPresent(surname -> resourceUrl.append("&surname=").append(surname));
        userFilter.getUsername().ifPresent(username -> resourceUrl.append("&username=").append(username));
        userFilter.getOffset().ifPresent(offset -> resourceUrl.append("&offset=").append(offset));
        userFilter.getLimit().ifPresent(limit -> resourceUrl.append("&limit=").append(limit));

        return restTemplate.getForObject(resourceUrl.toString(), String.class);

    }

    public UserSaveDto save(UserSaveDto userSaveDto){
        StringBuilder resourceUrl = new StringBuilder("http://localhost:8080/users");
        HttpEntity<UserSaveDto> request = new HttpEntity<>(new UserSaveDto()
                .setUsername(userSaveDto.getUsername())
                .setSurname(userSaveDto.getSurname())
                .setName(userSaveDto.getName())
                .setPassword(userSaveDto.getPassword()));
        return  restTemplate.postForObject(resourceUrl.toString(),request,UserSaveDto.class);
    }

    public Boolean update(UserSaveDto userSaveDto){
        StringBuilder resourceUrl = new StringBuilder("http://localhost:8080/users");
        HttpEntity<UserSaveDto> request = new HttpEntity<>(new UserSaveDto()
                .setId(userSaveDto.getId())
                .setUsername(userSaveDto.getUsername())
                .setSurname(userSaveDto.getSurname())
                .setName(userSaveDto.getName())
                .setPassword(userSaveDto.getPassword()));
       restTemplate.put(resourceUrl.toString(),request);
       return true;
    }

    public Boolean delete(Long id){
        StringBuilder resourceUrl = new StringBuilder("http://localhost:8080/users/");
        if (id != null){
            resourceUrl.append(id);
        }
        restTemplate.delete(resourceUrl.toString());
        return true;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
