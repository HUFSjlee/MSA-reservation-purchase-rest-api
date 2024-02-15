package com.service.activitiesservice.like.presentation.controller;

import com.service.activitiesservice.common.response.BaseResponse;
import com.service.activitiesservice.like.domain.service.ApiService;
import com.service.activitiesservice.like.domain.service.LikeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/likes")
public class LikeController {
    private final LikeService likeService;
    private final ApiService apiService;
    @PostMapping("/{token}/like")
    public ResponseEntity likesReaction(@PathVariable String token, @RequestBody Map<String, Long> requestBody){
        Long postId = requestBody.get("postId");
        Long userId = apiService.getUserId(token);
        System.out.println("api요청 끝"+userId);
        var response = likeService.likesReaction(userId, postId);

        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponse.success(response));
    }
}
