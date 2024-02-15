package com.service.userservice.domain.service;

import com.google.firebase.auth.FirebaseAuthException;
import com.service.userservice.common.exception.LoginException;
import com.service.userservice.common.exception.UsernameNotFoundException;
import com.service.userservice.config.jwt.JwtTokenProvider;
import com.service.userservice.domain.entity.User;
import com.service.userservice.domain.mapper.UserMapper;
import com.service.userservice.infrastructure.UserRepository;
import com.service.userservice.presentation.dto.SignInDto;
import com.service.userservice.presentation.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final FirebaseService firebaseService;
    private final JwtTokenProvider jwtTokenProvider;
    @Transactional
    public UserDTO.CreateResponse signUp(UserDTO.CreateRequest request) throws Exception {
        var user = userMapper.toEntity(request);

        if (userRepository.findByUserEmail(user.getUserEmail()).isPresent()){
            throw new Exception("이미 가입하신 이메일입니다.");
        }

        user.encodePassword(passwordEncoder);

        var savedMember = userRepository.save(user);

        return UserDTO.CreateResponse.builder()
                .id(savedMember.getId())
                .build();
    }

//    public UserDTO.FindResponse findById(Long id) {
//        User user = userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("멤버가 존재하지 않습니다. id = " + id));
//        return userMapper.toFindResponse(user);
//    }

    @Transactional
    public String login(UserDTO.CreateRequest request) {
        //멤버 이메일 체크
        User user = userRepository.findByUserEmail(request.getUserEmail())
                .orElseThrow(() -> new LoginException("멤버를 조회할 수 없습니다."));

        //멤버 비밀번호 체크
        if (passwordEncoder.matches(request.getUserPassword(), user.getUserPassword())) {
            throw new LoginException("비밀번호가 일치하지 않습니다.");
        }
        SignInDto loginUser = new SignInDto();
        loginUser.setUserId(user.getId());
        loginUser.setUserName(user.getUsername());
        loginUser.setUserEmail(user.getUserEmail());
        String token =  jwtTokenProvider.createToken(loginUser);
        return "로그인 성공: "+token;
    }

    @Transactional
    public String uploadAndSaveProfileImage(MultipartFile file, String nameFile, UserDTO.CreateRequest request) throws IOException, FirebaseAuthException {
        String imageUrl = firebaseService.uploadFiles(file, nameFile);

        // 이미지 URL을 Member 엔티티에 저장
        updateProfileImage(request.getUserEmail(), imageUrl);

        return imageUrl;
    }

    @Transactional
    public void updateProfileImage(String email, String imageUrl) {
        User user = userRepository.findByUserEmail(email)
                .orElseThrow(() -> new LoginException("Member not found with email: " + email));

        User updatedUser = User.builder()
                .id(user.getId())
                .userName(user.getUsername())
                .userEmail(user.getUserEmail())
                .userPassword(user.getUserPassword())
                .userProfileImage(imageUrl)  // 업데이트된 이미지 URL 설정
                .userGreetings(user.getUserGreetings())
                .build();

        userRepository.save(updatedUser);
    }

    @Transactional
    public UserDTO.UpdateResponse update(Long id, UserDTO.UpdateRequest request) {
        User user = userRepository.findById(id).orElseThrow(()-> new UsernameNotFoundException("유저 정보가 없습니다. id = " + id));

        User updateUser = User.builder()
                .id(id)
                .userName(request.getUserName())
                .userProfileImage(request.getUserProfileImage())
                .userGreetings(request.getUserGreetings())
                .build();
        return userMapper.toUpdateResponse(updateUser);
    }

    @Transactional
    public UserDTO.UpdatePasswordResponse updatePassword(Long id, UserDTO.UpdatePasswordRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("유저 정보가 없습니다. id " + id));

        User userUpdatedPassword = User.builder()
                .userPassword(request.getUserPassword())
                .build();

        return userMapper.toUpdatePasswordResponse(userUpdatedPassword);
    }

}
