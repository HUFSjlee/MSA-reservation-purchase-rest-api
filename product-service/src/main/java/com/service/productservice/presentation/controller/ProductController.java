package com.service.productservice.presentation.controller;

import com.service.productservice.common.response.BaseResponse;
import com.service.productservice.domain.entity.Product;
import com.service.productservice.domain.service.ProductService;
import com.service.productservice.presentation.dto.ProductDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public BaseResponse<ProductDTO.CreateResponse> create(@RequestBody @Valid ProductDTO.CreateRequest request) {
        //var responseProduct = productService.create(request);
        //return responseProduct;
        return BaseResponse.success(productService.create(request));
    }

    @GetMapping("/all")
    public Page<Product> readAll() {
        PageRequest pageRequest = PageRequest.of(0,10);
        return productService.readAll(pageRequest);
    }

    @GetMapping("/{id}")
    public BaseResponse<ProductDTO.FindResponse> read(@PathVariable Long id) {
        return BaseResponse.success(productService.read(id));
    }

    @GetMapping("/search")
    public Page<Product> search(@RequestParam("keyword") String keyword,
                                @RequestParam(value = "page", defaultValue = "0") int page,
                                @RequestParam(value = "size", defaultValue = "5") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return productService.search(keyword, pageRequest);
    }

    @PutMapping("/{id}")
    public BaseResponse<ProductDTO.UpdateResponse> update(@PathVariable Long id, @Valid @RequestBody ProductDTO.UpdateRequest request) {
        return BaseResponse.success(productService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public BaseResponse<ProductDTO.DeleteResponse> delete(@PathVariable Long id) {
        var deleteResponse = productService.delete(id);
        return BaseResponse.success(deleteResponse);
    }
}
