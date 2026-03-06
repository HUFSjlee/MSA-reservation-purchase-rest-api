package com.service.orderservice.domain.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "stock-service")
public interface StockFeignClient {

    @PostMapping("/stocks/mock/payment-entry")
    StockApiResponse<StockPaymentEntryResponse> enterPayment(@RequestBody StockPaymentEntryRequest request);

    @PostMapping("/stocks/mock/payment")
    StockApiResponse<StockPaymentResponse> pay(@RequestBody StockPaymentRequest request);

    record StockApiResponse<T>(
            String code,
            String msg,
            T result,
            String timestamp
    ) {
    }

    record StockPaymentEntryRequest(
            Long userId,
            Long productId
    ) {
    }

    record StockPaymentRequest(
            String paymentSessionId
    ) {
    }

    record StockPaymentEntryResponse(
            String paymentSessionId,
            Long userId,
            Long productId,
            Integer remainingStock
    ) {
    }

    record StockPaymentResponse(
            String paymentSessionId,
            Long userId,
            Long productId,
            Boolean success,
            String reason,
            Integer remainingStock
    ) {
    }
}

