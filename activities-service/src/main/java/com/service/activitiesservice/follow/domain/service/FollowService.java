package com.service.activitiesservice.follow.domain.service;

import com.service.activitiesservice.follow.domain.entity.Follow;
import com.service.activitiesservice.follow.infrastructure.FollowRepository;

import com.service.activitiesservice.follow.presentation.dto.FollowDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class FollowService {
    private final FollowRepository followRepository;

    @Transactional
    public FollowDTO.CreateResponse newCreateFollow(FollowDTO.CreateRequest request) {

        Follow followEntity = Follow.builder()
                .following(request.getFollowing())
                .follower(request.getFollower())
                .build();

        followEntity = followRepository.save(followEntity);

        return FollowDTO.CreateResponse.builder()
                .id(followEntity.getId())
                .following(request.getFollowing())
                .follower(request.getFollower())
                .build();
    }
}


