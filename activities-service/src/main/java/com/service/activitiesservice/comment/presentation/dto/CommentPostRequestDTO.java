package com.service.activitiesservice.comment.presentation.dto;

import com.service.activitiesservice.comment.domain.entity.Comment;
import com.service.activitiesservice.comment.infrastructure.CommentRepository;
import com.service.activitiesservice.common.exception.NotFoundPostException;
import com.service.activitiesservice.post.infrastructure.PostRepository;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommentPostRequestDTO {
    private Long postId;

    private String commentText;


    public Comment toEntity(PostRepository postRepository, CommentRepository commentRepository) {
        return Comment.builder()
                .post(postRepository.findById(this.postId).orElseThrow(NotFoundPostException::new))
                .commentText(this.commentText)
                .build();
    }
}