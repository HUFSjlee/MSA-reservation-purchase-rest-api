package com.service.orderservice.domain.service;

import com.service.orderservice.domain.client.StockFeignClient;
import lombok.RequiredArgsConstructor;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MockStockClient {

    private final StockFeignClient stockFeignClient;

    @Retry(name = "stockApi", fallbackMethod = "enterPaymentFallback")
    @CircuitBreaker(name = "stockApi", fallbackMethod = "enterPaymentFallback")
    public StockPaymentEntryResult enterPayment(Long userId, Long productId) {
        StockFeignClient.StockApiResponse<StockFeignClient.StockPaymentEntryResponse> response =
                stockFeignClient.enterPayment(new StockFeignClient.StockPaymentEntryRequest(userId, productId));

        if (response == null || response.result() == null) {
            throw new IllegalStateException("Invalid response from stock-service");
        }

        StockFeignClient.StockPaymentEntryResponse result = response.result();
        return new StockPaymentEntryResult(
                result.paymentSessionId(),
                result.userId(),
                result.productId(),
                result.remainingStock() == null ? 0 : result.remainingStock()
        );
    }

    @Retry(name = "stockApi", fallbackMethod = "payFallback")
    @CircuitBreaker(name = "stockApi", fallbackMethod = "payFallback")
    public StockPaymentResult pay(String paymentSessionId) {
        StockFeignClient.StockApiResponse<StockFeignClient.StockPaymentResponse> response =
                stockFeignClient.pay(new StockFeignClient.StockPaymentRequest(paymentSessionId));

        if (response == null || response.result() == null) {
            throw new IllegalStateException("Invalid response from stock-service");
        }

        StockFeignClient.StockPaymentResponse result = response.result();
        return new StockPaymentResult(
                result.paymentSessionId(),
                result.userId(),
                result.productId(),
                Boolean.TRUE.equals(result.success()),
                result.reason(),
                result.remainingStock() == null ? 0 : result.remainingStock()
        );
    }

    public StockPaymentEntryResult enterPaymentFallback(Long userId, Long productId, Throwable throwable) {
        throw new IllegalStateException("Stock service is temporarily unavailable for payment-entry", throwable);
    }

    public StockPaymentResult payFallback(String paymentSessionId, Throwable throwable) {
        return new StockPaymentResult(
                paymentSessionId,
                null,
                null,
                false,
                "STOCK_SERVICE_TEMPORARILY_UNAVAILABLE",
                0
        );
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
