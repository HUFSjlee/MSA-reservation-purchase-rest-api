package com.service.activitiesservice.follow.infrastructure;

import com.service.activitiesservice.follow.domain.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {

}
