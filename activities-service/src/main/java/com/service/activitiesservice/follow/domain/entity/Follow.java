package com.service.activitiesservice.follow.domain.entity;


import com.service.activitiesservice.common.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@NoArgsConstructor
@Getter
@Table(name = "follow")
public class Follow extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "following")
    private Long following;

    @Column(name = "follower")
    private Long follower;
}
