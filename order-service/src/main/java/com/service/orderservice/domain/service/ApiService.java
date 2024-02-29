package com.service.orderservice.domain.service;

import com.service.orderservice.common.exception.OutOfStockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
public class ApiService {
    public Long getUserId(String token){
        RestTemplate restTemplate = new RestTemplate();
        String url = UriComponentsBuilder.fromHttpUrl("http://user-service:8081")
                .path("/users/" + token)
                .toUriString();

        ResponseEntity<Long> response = restTemplate.getForEntity(url, Long.class);
        return response.getBody();
    }

    public void checkStockInfo(Long productId, int orderQuantity) {
        RestTemplate restTemplate = new RestTemplate();
        String url = UriComponentsBuilder.fromHttpUrl("http://stock-service:8086")
                .path("/stocks/" + productId)
                .toUriString();

        ResponseEntity<Integer> responseEntity = restTemplate.getForEntity(url, Integer.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            int stockQuantity = responseEntity.getBody();
            if (stockQuantity < orderQuantity) {
                // 재고가 주문 수량보다 부족한 경우
                throw new OutOfStockException("재고가 부족합니다.");
            }
        } else {
            // 다른 예외 상황에 대한 로직 추가
            throw new RuntimeException("Failed to retrieve stock information for productId: " + productId);
        }
    }

    public void reduceStock(Long productId, int quantity) {
        RestTemplate restTemplate = new RestTemplate();
        String url = UriComponentsBuilder.fromHttpUrl("http://stockManagement-service:8086")
                .path("/stocks/reduce")
                .toUriString();

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("productId", productId);
        requestBody.put("quantity", quantity);

        restTemplate.postForEntity(url, requestBody, Void.class);
    }
}
