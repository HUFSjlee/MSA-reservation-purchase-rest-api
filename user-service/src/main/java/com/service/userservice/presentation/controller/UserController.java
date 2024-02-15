package com.service.userservice.presentation.controller;

import com.google.firebase.auth.FirebaseAuthException;
import com.service.userservice.common.response.BaseResponse;
import com.service.userservice.domain.service.EmailService;
import com.service.userservice.domain.service.UserService;
import com.service.userservice.config.jwt.JwtTokenProvider;
import com.service.userservice.presentation.dto.SignInDto;
import com.service.userservice.presentation.dto.UserDTO;
import io.swagger.annotations.ApiOperation;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final EmailService emailService;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 회원 가입
     * */
    @PostMapping("/signup")
    public ResponseEntity join(@Valid @RequestBody UserDTO.CreateRequest request) throws Exception {
        var response = userService.signUp(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponse.success(response));
    }


    /**
     * 이메일 인증 코드 보내기
     * */
    @ResponseBody
    @PostMapping("/sign-up/emailauth")
    public BaseResponse<String> EmailCheck(@RequestParam String emailRequest) throws MessagingException, UnsupportedEncodingException {
        var authCode = emailService.sendEmail(emailRequest);
        return BaseResponse.success(authCode);
    }

    /**
     * 유저 로그인
     * */
    @PostMapping("/login")
    @ApiOperation(value="로그인")
    public ResponseEntity<String> login(@Valid @RequestBody UserDTO.CreateRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }


    /**
     * 로그인한 회원의 토큰으로 회원 찾기
     * */
    @GetMapping("/{token}")
    public ResponseEntity<Long> findById(@PathVariable String token) {
        System.out.println("getUser진입");
        UserDTO.FindResponse user = jwtTokenProvider.getUserInfo(token);
        System.out.println("jwt가 준 userId : "+ user.getId());
        return ResponseEntity.ok().body(user.getId());
    }

    /**
     * 프로필 이미지 등록
     * */
    @PutMapping("/set-profile-image")
    public ResponseEntity<String> setProfileImage(@RequestParam("file") MultipartFile file, @RequestParam("email") String email) throws IOException, FirebaseAuthException {
        //토큰 검증 먼저
        // USERDTO.RE response = jwtTokenProvider.getUserInfo(token);
        // response 들고 DB가서 정보를 가져오든 or
        // 이미 response가 원하는 정보를 가지고 있다면 imageurl 업데이트 실행 seq ...
        String imageUrl = userService.uploadAndSaveProfileImage(file, "someName", createRequestWithProfileImage(email));
        return ResponseEntity.ok().body(imageUrl);
    }
    private UserDTO.CreateRequest createRequestWithProfileImage(String email) {
        return UserDTO.CreateRequest.builder()
                .userEmail(email)
                .build();
    }

    /**
     * 유저 이름, 유저 프로필 이미지, 유저 소개말 수정
     * */
    @PutMapping("/update/{id}")
    public BaseResponse<UserDTO.UpdateResponse> update(@PathVariable Long id, @Validated @RequestBody UserDTO.UpdateRequest request) {
        return BaseResponse.success(userService.update(id, request));
    }

    /**
     * 유저 비밀번호 수정
     * */
    @PutMapping("/{id}")
    public BaseResponse<UserDTO.UpdatePasswordResponse> updatePassword(@PathVariable Long id, @Validated @RequestBody UserDTO.UpdatePasswordRequest request) {
        return BaseResponse.success(userService.updatePassword(id,request));
    }
}
