package com.service.activitiesservice.like.domain.service;

import com.service.activitiesservice.comment.infrastructure.CommentRepository;
import com.service.activitiesservice.common.exception.NotFoundPostException;
import com.service.activitiesservice.like.infrastructure.LikeRepository;
import com.service.activitiesservice.like.presentation.dto.LikeDTO;
import com.service.activitiesservice.post.domain.entity.Post;
import com.service.activitiesservice.post.infrastructure.PostRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;


    public LikeDTO.CreateResponse likesReaction(Long userId, Long postId) {
        //User user = userRepository.findById(userId).orElseThrow(()->new NotFoundUserException("유저가 없습니다."));
        //User user = apiService.getUser(userId);
        Post post = postRepository.findById(postId).orElseThrow(()->new NotFoundPostException("게시글이 없습니다."));

        LikeDTO.CreateResponse response= null;
//        if(user.getLikes().stream().anyMatch(like -> like.getPost().equals(post))) {
//            likeRepository.deleteByIdAndPost(2L,post);
//            response = LikeDTO.CreateResponse.builder()
//                    .userId(user.getId())
//                    .postId(post.getPostId())
//                    .likeStatus(false)
//                    .build();
//        } else {
//            likeRepository.save(Like.builder().post(post).user(user).build());
//            response = LikeDTO.CreateResponse.builder()
//                    .userId(user.getId())
//                    .postId(post.getPostId())
//                    .likeStatus(true)
//                    .build();
//        }
        //통신을 통해 user컨테이너로 부터 원하는 정보를 가져오세요
        return response;
    }
}
