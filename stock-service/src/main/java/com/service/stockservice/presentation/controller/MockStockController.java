package com.service.stockservice.presentation.controller;

import com.service.stockservice.common.response.BaseResponse;
import com.service.stockservice.domain.service.MockStockService;
import com.service.stockservice.presentation.dto.MockStockDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stocks/mock")
public class MockStockController {

    private final MockStockService mockStockService;

    @GetMapping("/{productId}/remaining")
    public BaseResponse<MockStockDto.RemainingStockResponse> getRemainingStock(@PathVariable Long productId) {
        return BaseResponse.success(mockStockService.getRemainingStock(productId));
    }

    @PostMapping("/payment-entry")
    public BaseResponse<MockStockDto.PaymentEntryResponse> enterPayment(@RequestBody MockStockDto.PaymentEntryRequest request) {
        return BaseResponse.success(mockStockService.enterPayment(request));
    }

    @PostMapping("/payment")
    public BaseResponse<MockStockDto.PaymentResponse> pay(@RequestBody MockStockDto.PaymentRequest request) {
        return BaseResponse.success(mockStockService.pay(request));
    }
}

