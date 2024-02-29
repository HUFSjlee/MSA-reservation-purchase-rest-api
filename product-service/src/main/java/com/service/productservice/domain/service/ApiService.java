package com.service.productservice.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
public class ApiService {
    public void saveStockInfo(Long productId, int productStock) {
        RestTemplate restTemplate = new RestTemplate();
        String url = UriComponentsBuilder.fromHttpUrl("http://stock-service:8086")
                .path("/stocks/save")
                .toUriString();

        Map<Long, Integer> request = new HashMap<>();
        request.put(productId, productStock);
        System.out.println("123:::"+request);
        restTemplate.postForObject(url, request, Map.class);
    }
}
