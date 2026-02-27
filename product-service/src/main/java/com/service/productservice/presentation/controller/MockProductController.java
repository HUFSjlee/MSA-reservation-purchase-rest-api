package com.service.productservice.presentation.controller;

import com.service.productservice.common.response.BaseResponse;
import com.service.productservice.domain.service.MockProductService;
import com.service.productservice.presentation.dto.MockProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products/mock")
public class MockProductController {

    private final MockProductService mockProductService;

    @GetMapping
    public BaseResponse<List<MockProductDto.ProductSummary>> getProducts() {
        return BaseResponse.success(mockProductService.getMockProducts());
    }

    @GetMapping("/{productId}")
    public BaseResponse<MockProductDto.ProductDetail> getProduct(@PathVariable Long productId) {
        return BaseResponse.success(mockProductService.getMockProductDetail(productId));
    }
}

