package com.service.orderservice.presentation.dto;

public class MockOrderDto {

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
            boolean success,
            String reason,
            Long orderId,
            Long userId,
            Long productId,
            int remainingStock
    ) {
    }

    public record OrderInfoResponse(
            Long orderId,
            Long userId,
            Long productId,
            int quantity,
            String orderStatus
    ) {
    }
}

