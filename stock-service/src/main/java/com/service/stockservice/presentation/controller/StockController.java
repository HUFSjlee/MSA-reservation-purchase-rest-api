package com.service.stockservice.presentation.controller;

import com.service.stockservice.common.response.BaseResponse;
import com.service.stockservice.domain.service.StockService;
import com.service.stockservice.presentation.dto.StockDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/stocks")
public class StockController {
    private final StockService stockService;

    @PostMapping("/save")
    public BaseResponse<Map<Long, Integer>> saveStockInfo(@RequestBody @Valid Map<Long, Integer> request){
        stockService.saveStockInfo(request);
        return BaseResponse.success(request);
    }

    @GetMapping("/get")
    public BaseResponse<Integer> getStockInfo(@RequestBody @Valid StockDto.CreateRequest request) {
        return BaseResponse.success(stockService.getStockInfo(request.getProductId()));
    }
}
