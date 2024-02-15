package com.service.userservice.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

public class UserDTO {

    @Getter
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BaseResponse {
        private Long id;

        @JsonProperty(value = "user_name")
        private String userName;

        @JsonProperty(value = "user_email")
        private String userEmail;

        @NotBlank(message = "비밀번호를 입력해주세요")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,30}$",
                message = "비밀번호는 8~30 자리이면서 1개 이상의 알파벳, 숫자, 특수문자를 포함해야합니다.")
        @JsonProperty(value = "user_password")
        private String userPassword;

        @JsonProperty(value = "user_profile_image")
        private String userProfileImage;

        @JsonProperty(value = "user_greetings")
        private String userGreetings;

    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateRequest {
        private Long id;
        @JsonProperty(value = "user_name")
        private String userName;
        @JsonProperty(value = "user_email")
        private String userEmail;
        @JsonProperty(value = "user_password")
        private String userPassword;
        @JsonProperty(value = "user_greetings")
        private String userGreetings;
        @JsonProperty(value = "user_profile_image")
        private String userProfileImage;
        private String checkedPassword;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateResponse {
        private Long id;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FindResponse {
        private Long id;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateRequest {
        private Long id;
        @JsonProperty(value = "user_name")
        private String userName;
        @JsonProperty(value = "user_profile_image")
        private String userProfileImage;
        @JsonProperty(value = "user_greetings")
        private String userGreetings;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateResponse {
        private Long id;
        @JsonProperty(value = "user_name")
        private String userName;
        @JsonProperty(value = "user_profile_image")
        private String userProfileImage;
        @JsonProperty(value = "user_greetings")
        private String userGreetings;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdatePasswordRequest {
        @JsonProperty(value = "user_password")
        private String userPassword;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdatePasswordResponse {
        @JsonProperty(value = "user_password")
        private String userPassword;
    }
}
