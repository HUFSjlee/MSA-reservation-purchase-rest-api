package com.service.activitiesservice.post.domain.entity;


import com.service.activitiesservice.comment.domain.entity.Comment;
import com.service.activitiesservice.common.base.BaseEntity;
import com.service.activitiesservice.like.domain.entity.Like;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@SuperBuilder
@NoArgsConstructor
@Getter
@Table(name = "post")
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "post_content")
    private String postContent;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<Like> likes;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<Comment> comments;

    public void update(Long userId, String postContent) {
        this.userId = userId;
        this.postContent = postContent;
    }
}
