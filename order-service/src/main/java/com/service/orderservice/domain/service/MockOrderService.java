package com.service.orderservice.domain.service;

import com.service.orderservice.common.exception.NotFoundResourceException;
import com.service.orderservice.domain.entity.Order;
import com.service.orderservice.domain.entity.OrderStatus;
import com.service.orderservice.infrastructure.OrderRepository;
import com.service.orderservice.presentation.dto.MockOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MockOrderService {

    private final MockStockClient mockStockClient;
    private final OrderRepository orderRepository;

    public MockOrderDto.PaymentEntryResponse enterPayment(MockOrderDto.PaymentEntryRequest request) {
        MockStockClient.StockPaymentEntryResult result = mockStockClient.enterPayment(request.userId(), request.productId());
        return new MockOrderDto.PaymentEntryResponse(
                result.paymentSessionId(),
                result.userId(),
                result.productId(),
                result.remainingStock()
        );
    }

    @Transactional
    public MockOrderDto.PaymentResponse pay(MockOrderDto.PaymentRequest request) {
        MockStockClient.StockPaymentResult result = mockStockClient.pay(request.paymentSessionId());
        if (!result.success()) {
            return new MockOrderDto.PaymentResponse(
                    result.paymentSessionId(),
                    false,
                    result.reason(),
                    null,
                    result.userId(),
                    result.productId(),
                    result.remainingStock()
            );
        }

        Order order = orderRepository.save(Order.builder()
                .userId(result.userId())
                .productId(result.productId())
                .quantity(1)
                .orderStatus(OrderStatus.PAYMENT_COMPLETED)
                .build());

        return new MockOrderDto.PaymentResponse(
                result.paymentSessionId(),
                true,
                result.reason(),
                order.getId(),
                order.getUserId(),
                order.getProductId(),
                result.remainingStock()
        );
    }

    @Transactional(readOnly = true)
    public MockOrderDto.OrderInfoResponse getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundResourceException("Order not found: " + orderId));

        return new MockOrderDto.OrderInfoResponse(
                order.getId(),
                order.getUserId(),
                order.getProductId(),
                order.getQuantity(),
                order.getOrderStatus().name()
        );
    }
}

