package com.service.stockservice.presentation.dto;

public class MockStockDto {

    public record PaymentEntryRequest(
            Long userId,
            Long productId
    ) {
    }

    public record PaymentEntryResponse(
            String paymentSessionId,
            Long userId,
            Long productId,
            int remainingStock
    ) {
    }

    public record PaymentRequest(
            String paymentSessionId
    ) {
    }

    public record PaymentResponse(
            String paymentSessionId,
            Long userId,
            Long productId,
            boolean success,
            String reason,
            int remainingStock
    ) {
    }

    public record RemainingStockResponse(
            Long productId,
            int remainingStock
    ) {
    }
}

