package com.service.activitiesservice.like.domain.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ApiService {
    public Long getUserId(String token){
        System.out.println("apiservice");
        RestTemplate restTemplate = new RestTemplate();
        String url = UriComponentsBuilder.fromHttpUrl("http://user-service:8081")
                .path("/users/" + token)
                .toUriString();

        ResponseEntity<Long> response = restTemplate.getForEntity(url, Long.class);
        return response.getBody();
    }
}
