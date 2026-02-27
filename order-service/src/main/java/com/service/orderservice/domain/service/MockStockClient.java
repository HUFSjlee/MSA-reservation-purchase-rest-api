package com.service.orderservice.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class MockStockClient {

    private final RestTemplateBuilder restTemplateBuilder;

    @Value("${mock.stock-service-base-url:http://stock-service:8086}")
    private String stockServiceBaseUrl;

    public StockPaymentEntryResult enterPayment(Long userId, Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        Map<String, Object> response = restTemplate.postForObject(
                stockServiceBaseUrl + "/stocks/mock/payment-entry",
                Map.of("userId", userId, "productId", productId),
                Map.class
        );
        Map<String, Object> result = extractResult(response);
        return new StockPaymentEntryResult(
                String.valueOf(result.get("paymentSessionId")),
                toLong(result.get("userId")),
                toLong(result.get("productId")),
                toInt(result.get("remainingStock"))
        );
    }

    public StockPaymentResult pay(String paymentSessionId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        Map<String, Object> response = restTemplate.postForObject(
                stockServiceBaseUrl + "/stocks/mock/payment",
                Map.of("paymentSessionId", paymentSessionId),
                Map.class
        );
        Map<String, Object> result = extractResult(response);
        return new StockPaymentResult(
                String.valueOf(result.get("paymentSessionId")),
                toLong(result.get("userId")),
                toLong(result.get("productId")),
                Boolean.parseBoolean(String.valueOf(result.get("success"))),
                String.valueOf(result.get("reason")),
                toInt(result.get("remainingStock"))
        );
    }

    private Map<String, Object> extractResult(Map<String, Object> response) {
        if (response == null || !response.containsKey("result")) {
            throw new IllegalStateException("Invalid response from stock-service");
        }
        return (Map<String, Object>) response.get("result");
    }

    private Long toLong(Object value) {
        return value == null ? null : Long.valueOf(String.valueOf(value));
    }

    private int toInt(Object value) {
        return value == null ? 0 : Integer.parseInt(String.valueOf(value));
    }

    public record StockPaymentEntryResult(
            String paymentSessionId,
            Long userId,
            Long productId,
            int remainingStock
    ) {
    }

    public record StockPaymentResult(
            String paymentSessionId,
            Long userId,
            Long productId,
            boolean success,
            String reason,
            int remainingStock
    ) {
    }
}

