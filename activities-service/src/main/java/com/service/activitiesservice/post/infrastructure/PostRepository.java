package com.service.activitiesservice.post.infrastructure;


import com.service.activitiesservice.post.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
