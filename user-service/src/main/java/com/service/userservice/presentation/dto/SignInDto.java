package com.service.userservice.presentation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SignInDto {
    private Long userId;
    private String userName;
    private String userEmail;
}
