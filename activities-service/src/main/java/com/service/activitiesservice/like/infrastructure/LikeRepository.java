package com.service.activitiesservice.like.infrastructure;


import com.service.activitiesservice.like.domain.entity.Like;
import com.service.activitiesservice.post.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
   //void deleteByIdAndPost(Long userId, Post post);
}
