package com.service.newsfeedservice.presentation.controller;


import com.service.newsfeedservice.common.response.BaseResponse;
import com.service.newsfeedservice.domain.service.NewsfeedService;
import com.service.newsfeedservice.presentation.dto.NewsfeedDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/newsfeeds")
public class NewsfeedController {
    private final NewsfeedService newsfeedService;

    @GetMapping
    public ResponseEntity read(@RequestBody NewsfeedDTO.CreateRequest request) {
        var response = newsfeedService.read(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponse.success(response));
    }
}
