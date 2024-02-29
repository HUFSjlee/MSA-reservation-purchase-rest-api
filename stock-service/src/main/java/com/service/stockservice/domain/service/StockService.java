package com.service.stockservice.domain.service;

import com.service.stockservice.common.exception.OutOfStockException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class StockService {
    private final RedisTemplate<Long, Integer> redisTemplate;

    @Transactional
    public void saveStockInfo(Map<Long, Integer> stockInfo) {
        stockInfo.forEach((productId, stock) -> redisTemplate.opsForValue().set(productId, stock));
    }

    @Transactional
    public int getStockInfo(Long productId) {
        Integer stock = redisTemplate.opsForValue().get(productId);

        if (stock != null) {
            return stock;
        } else {
            throw new OutOfStockException("Out of stock for productId: " + productId);
        }
    }
}
