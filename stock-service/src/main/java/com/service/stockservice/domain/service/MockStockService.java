package com.service.stockservice.domain.service;

import com.service.stockservice.common.exception.OutOfStockException;
import com.service.stockservice.presentation.dto.MockStockDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class MockStockService {

    private static final Long RESERVATION_PRODUCT_ID = 1L;
    private static final int RESERVATION_PRODUCT_TOTAL_STOCK = 10;
    private static final int DEFAULT_TOTAL_STOCK = 1000;
    private static final Duration PAYMENT_SESSION_TTL = Duration.ofMinutes(30);

    private final StringRedisTemplate redisTemplate;

    @Value("${mock.reservation-open-at:14:00}")
    private String reservationOpenAt;

    private final Object lock = new Object();

    public MockStockDto.RemainingStockResponse getRemainingStock(Long productId) {
        synchronized (lock) {
            initializeProductIfAbsent(productId);
            return new MockStockDto.RemainingStockResponse(productId, calculateRemainingStock(productId));
        }
    }

    public MockStockDto.PaymentEntryResponse enterPayment(MockStockDto.PaymentEntryRequest request) {
        synchronized (lock) {
            Long productId = request.productId();
            initializeProductIfAbsent(productId);
            validateOpenTimeIfReservationProduct(productId);

            int remainingStock = calculateRemainingStock(productId);
            if (remainingStock <= 0) {
                throw new OutOfStockException("Out of stock for productId: " + productId);
            }

            incrementReserved(productId);
            String paymentSessionId = UUID.randomUUID().toString();
            String sessionKey = sessionKey(paymentSessionId);
            redisTemplate.opsForHash().putAll(sessionKey, Map.of(
                    "userId", String.valueOf(request.userId()),
                    "productId", String.valueOf(productId),
                    "status", "ENTERED"
            ));
            redisTemplate.expire(sessionKey, PAYMENT_SESSION_TTL);

            return new MockStockDto.PaymentEntryResponse(
                    paymentSessionId,
                    request.userId(),
                    productId,
                    calculateRemainingStock(productId)
            );
        }
    }

    public MockStockDto.PaymentResponse pay(MockStockDto.PaymentRequest request) {
        synchronized (lock) {
            String sessionKey = sessionKey(request.paymentSessionId());
            Map<Object, Object> session = redisTemplate.opsForHash().entries(sessionKey);
            if (session.isEmpty()) {
                throw new IllegalArgumentException("Invalid paymentSessionId: " + request.paymentSessionId());
            }

            String status = String.valueOf(session.get("status"));
            if (!"ENTERED".equals(status)) {
                throw new IllegalStateException("Payment session is not active: " + request.paymentSessionId());
            }

            Long userId = Long.parseLong(String.valueOf(session.get("userId")));
            Long productId = Long.parseLong(String.valueOf(session.get("productId")));

            decrementReserved(productId);
            boolean failedByCustomer = ThreadLocalRandom.current().nextInt(100) < 20;

            if (failedByCustomer) {
                redisTemplate.opsForHash().put(sessionKey, "status", "FAILED");
                return new MockStockDto.PaymentResponse(
                        request.paymentSessionId(),
                        userId,
                        productId,
                        false,
                        "CUSTOMER_CANCELLED",
                        calculateRemainingStock(productId)
                );
            }

            incrementSold(productId);
            redisTemplate.opsForHash().put(sessionKey, "status", "COMPLETED");
            return new MockStockDto.PaymentResponse(
                    request.paymentSessionId(),
                    userId,
                    productId,
                    true,
                    "COMPLETED",
                    calculateRemainingStock(productId)
            );
        }
    }

    private void validateOpenTimeIfReservationProduct(Long productId) {
        if (!RESERVATION_PRODUCT_ID.equals(productId)) {
            return;
        }
        LocalTime openTime = LocalTime.parse(reservationOpenAt);
        LocalTime now = LocalTime.now(ZoneId.of("Asia/Seoul"));
        if (now.isBefore(openTime)) {
            throw new IllegalStateException("Reservation purchase is not open yet. Open at " + openTime);
        }
    }

    private void initializeProductIfAbsent(Long productId) {
        int totalStock = RESERVATION_PRODUCT_ID.equals(productId) ? RESERVATION_PRODUCT_TOTAL_STOCK : DEFAULT_TOTAL_STOCK;
        redisTemplate.opsForValue().setIfAbsent(totalKey(productId), String.valueOf(totalStock));
        redisTemplate.opsForValue().setIfAbsent(reservedKey(productId), "0");
        redisTemplate.opsForValue().setIfAbsent(soldKey(productId), "0");
    }

    private int calculateRemainingStock(Long productId) {
        int total = getInt(totalKey(productId));
        int reserved = getInt(reservedKey(productId));
        int sold = getInt(soldKey(productId));
        return Math.max(total - reserved - sold, 0);
    }

    private void incrementReserved(Long productId) {
        redisTemplate.opsForValue().increment(reservedKey(productId));
    }

    private void decrementReserved(Long productId) {
        redisTemplate.opsForValue().decrement(reservedKey(productId));
    }

    private void incrementSold(Long productId) {
        redisTemplate.opsForValue().increment(soldKey(productId));
    }

    private int getInt(String key) {
        String value = redisTemplate.opsForValue().get(key);
        return value == null ? 0 : Integer.parseInt(value);
    }

    private String totalKey(Long productId) {
        return "mock:stock:total:" + productId;
    }

    private String reservedKey(Long productId) {
        return "mock:stock:reserved:" + productId;
    }

    private String soldKey(Long productId) {
        return "mock:stock:sold:" + productId;
    }

    private String sessionKey(String paymentSessionId) {
        return "mock:stock:session:" + paymentSessionId;
    }
}

