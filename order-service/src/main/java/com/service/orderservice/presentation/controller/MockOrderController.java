package com.service.orderservice.presentation.controller;

import com.service.orderservice.common.response.BaseResponse;
import com.service.orderservice.domain.service.MockOrderService;
import com.service.orderservice.presentation.dto.MockOrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders/mock")
public class MockOrderController {

    private final MockOrderService mockOrderService;

    @PostMapping("/payment-entry")
    public BaseResponse<MockOrderDto.PaymentEntryResponse> enterPayment(
            @RequestBody MockOrderDto.PaymentEntryRequest request
    ) {
        return BaseResponse.success(mockOrderService.enterPayment(request));
    }

    @PostMapping("/payment")
    public BaseResponse<MockOrderDto.PaymentResponse> pay(
            @RequestBody MockOrderDto.PaymentRequest request
    ) {
        return BaseResponse.success(mockOrderService.pay(request));
    }

    @GetMapping("/{orderId}")
    public BaseResponse<MockOrderDto.OrderInfoResponse> getOrder(@PathVariable Long orderId) {
        return BaseResponse.success(mockOrderService.getOrder(orderId));
    }
}

