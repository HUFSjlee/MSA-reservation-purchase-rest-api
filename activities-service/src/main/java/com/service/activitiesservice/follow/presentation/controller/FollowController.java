package com.service.activitiesservice.follow.presentation.controller;

import com.service.activitiesservice.common.response.BaseResponse;
import com.service.activitiesservice.follow.domain.service.FollowService;

import com.service.activitiesservice.follow.presentation.dto.FollowDTO;
import com.service.activitiesservice.like.domain.service.ApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class FollowController {
    private final FollowService followService;
    private final ApiService apiService;
    @PostMapping("/follow")
    public ResponseEntity newCreateFollow(@RequestBody FollowDTO.CreateRequest request, @RequestParam String token){
        System.out.println("진입");
        Long userId = apiService.getUserId(token);
        System.out.println("가져온거 : "+userId);
        FollowDTO.CreateRequest fr = FollowDTO.CreateRequest.builder()
                .following(request.getFollowing())
                .follower(userId)
                .build();
        var response = followService.newCreateFollow(fr);
       // String inputMessage = response.getFollower()+"님이"+response.getFollowing()+"님을"+ NewsfeedType.FOLLOW.getMessage();
       // newsfeedService.createnewsfeed(response.getFollower(),response.getFollowing(),inputMessage);
        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponse.success(response));
    }

}
