package com.service.orderservice.presentation.controller;

import com.service.orderservice.common.response.BaseResponse;
import com.service.orderservice.domain.entity.Order;
import com.service.orderservice.domain.service.ApiService;
import com.service.orderservice.domain.service.OrderService;
import com.service.orderservice.presentation.dto.OrderDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final ApiService apiService;
    @PostMapping("/create")
    public BaseResponse<OrderDto.CreateResponse> create(@RequestParam String token, @RequestBody @Valid OrderDto.CreateRequest request) {
        Long userId = apiService.getUserId(token);
        return BaseResponse.success(orderService.create(request,userId));
    }

    @GetMapping("/all")
    public Page<Order> readAll() {
        PageRequest pageRequest = PageRequest.of(0,10);
        return orderService.readAll(pageRequest);
    }

    @GetMapping("/{id}")
    public BaseResponse<OrderDto.FindResponse> read(@PathVariable Long id) {
        return BaseResponse.success(orderService.read(id));
    }

    @GetMapping("/search")
    public Page<Order> search(@RequestParam("orderId") Long orderId,
                                @RequestParam(value = "page", defaultValue = "0") int page,
                                @RequestParam(value = "size", defaultValue = "5") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return orderService.search(orderId, pageRequest);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<OrderDto.DeleteResponse> delete(@PathVariable Long id) {
        var deleteResponse = orderService.delete(id);
        return BaseResponse.success(deleteResponse);
    }
}
