package com.service.activitiesservice.follow.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

public class FollowDTO {
    @Getter
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BaseResponse {
        @JsonProperty(value = "id")
        private Long id;
        @JsonProperty(value = "following")
        private Long following;
        @JsonProperty(value = "follower")
        private Long follower;
    }

    @Getter
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateRequest {
        @JsonProperty(value = "id")
        private Long id;
        @JsonProperty(value = "following")
        private Long following;
        @JsonProperty(value = "follower")
        private Long follower;
    }

    @Getter
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateResponse {
        @JsonProperty(value = "id")
        private Long id;
        @JsonProperty(value = "following")
        private Long following;
        @JsonProperty(value = "follower")
        private Long follower;
    }
}
