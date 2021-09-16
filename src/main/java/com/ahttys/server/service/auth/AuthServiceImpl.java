package com.ahttys.server.service.auth;

import com.ahttys.server.config.jwt.JwtFilter;
import com.ahttys.server.config.jwt.TokenProvider;
import com.ahttys.server.domain.user.Member;
import com.ahttys.server.dto.AuthDto;
import com.ahttys.server.dto.MessageDto;
import com.ahttys.server.repository.UserRepository;
import com.ahttys.server.util.error.ErrorCode;
import com.ahttys.server.util.error.exceptions.CustomException;
import com.ahttys.server.util.error.exceptions.DuplicationException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);


    @Override
    @Transactional
    public AuthDto.UserResponse createUser(AuthDto.CreateUser userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new CustomException("이미 가입되어 있는 유저입니다.", 400);
        }

        Member newMember = userRepository.save(userDto.toEntity(passwordEncoder));
        return AuthDto.UserResponse.builder()
                .email(newMember.getEmail())
                .name(newMember.getName())
                .build();
    }

    @Override
    public AuthDto.Token authentication(AuthDto.Login loginDto) {
        Optional<Member> user = userRepository.findByEmail(loginDto.getEmail());
        if (user.isPresent() && passwordEncoder.matches(loginDto.getPassword(), user.get().getPassword())) {
            String jwt = tokenProvider.createToken(user.get());
            return new AuthDto.Token(jwt);
        } else {
            throw new CustomException("로그인에 실패하였습니다.", 400);
        }
    }

    @Override
    @Transactional
    public MessageDto isValidEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new DuplicationException("존재하는 이메일 입니다.", ErrorCode.EMAIL_DUPLICATION);
        }
        return new MessageDto("사용 가능한 이메일 입니다.");
    }

    @Override
    @Transactional
    public MessageDto isValidName(String name) {
        if (userRepository.findByName(name).isPresent()) {
            throw new DuplicationException("존재하는 닉네임 입니다.", ErrorCode.NICKNAME_DUPLICATION);
        }
        return new MessageDto("사용 가능한 닉네임 입니다.");
    }
}
